package com.agilie.agmobilegiftinterface

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.agilie.agmobilegiftinterface.animation.GiftService
import com.agilie.agmobilegiftinterface.animation.GiftService.Companion.RES_ID
import com.agilie.agmobilegiftinterface.gravity.GravitySensorListener
import com.agilie.agmobilegiftinterface.gravity.physics.Physics2dViewGroup
import com.agilie.agmobilegiftinterface.gravity.physics.view.ViewGroupParams
import com.agilie.agmobilegiftinterface.gravity.physics.view.ViewParams
import com.agilie.agmobilegiftinterface.shake.ShakeBuilder

class AGMobileGiftInterfaceImpl : AGMobileGiftInterface {

    private var newGroup: Physics2dViewGroup? = null
    private var gravitySensor: GravitySensorListener? = null
    private var oldGroupViewX: Float = 0f
    private var oldGroupViewY: Float = 0f
    private var oldViewGroupList: MutableList<ViewGroupParams> = mutableListOf()
    private var contentFrame: FrameLayout? = null
    private var parent: ViewGroup? = null
    private var grantParent: ViewGroup? = null
    private var viewGroupsParams: ViewGroupParams? = null
    private var count = 0

    override fun startGravity(context: Context, viewGroup: ViewGroup) {
        onStartGravity(context, viewGroup)
    }

    override fun shake(view: View) = ShakeBuilder.Builder(view)

    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        Thread().run {
            context.startService(intent)
        }
    }

    private fun onStartGravity(context: Context, viewGroup: ViewGroup) {
        Log.d("TAG", "----------------------------------------------------------")
        contentFrame = FrameLayout(context)
        val frameParam = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        contentFrame?.layoutParams = frameParam

        parent = viewGroup
        grantParent = viewGroup.parent as ViewGroup
        grantParent?.removeView(viewGroup)

        newGroup = Physics2dViewGroup(context)

        initCustomViewGroup(context, viewGroup)

        oldViewGroupList.forEach {
            it.setViewGroupParam()
            contentFrame?.addView(it.oldViewGroup)
        }

        contentFrame?.addView(newGroup, viewGroup.layoutParams)
        grantParent?.addView(contentFrame)

        startSensorListener(context)
    }

    private fun startSensorListener(context: Context) {
        if (gravitySensor == null) {
            gravitySensor = GravitySensorListener(context)
        }
        gravitySensor?.onResumeSensor()
        gravitySensor?.gravityListener = (object : GravitySensorListener.onGravityListener {
            override fun onGravity(x: Float, y: Float) {
                newGroup?.physics2d?.onStartGravity(x, y)
            }
        })
    }


    private fun initCustomViewGroup(context: Context, viewGroup: View): View {
        if (viewGroup !is ViewGroup) {
            viewGroup.x += oldGroupViewX
            viewGroup.y += oldGroupViewY
            newGroup?.addView(viewGroup)

            val viewParam = ViewParams().apply {
                view = viewGroup
                x = viewGroup.x
                y = viewGroup.y
            }
            viewGroupsParams?.viewsList?.add(viewParam)

            Log.d("TAG", "newViewX= " + viewGroup.x + " newViewY= " + viewGroup.y)
            return viewGroup
        }

        oldGroupViewX = viewGroup.x
        oldGroupViewY = viewGroup.y

        Log.d("TAG", "groupViewX= " + oldGroupViewX + " groupViewY= " + oldGroupViewY)

        viewGroupsParams = ViewGroupParams()
        viewGroupsParams?.apply {
            oldViewGroup = viewGroup
            groupViewBottom = viewGroup.bottom
            groupViewLeft = viewGroup.left
            groupViewRight = viewGroup.right
            groupViewTop = viewGroup.top
            groupViewX = viewGroup.x
            groupViewY = viewGroup.y
        }

        viewGroupsParams?.let { oldViewGroupList.add(it) }

        while (viewGroup.childCount > 0) {
            val childView = viewGroup.getChildAt(0)
            //Log.d("TAG", "oldViewX= " + childView.x + " oldViewY= " + childView.y)
            viewGroup.removeView(childView)
            initCustomViewGroup(context, childView as View)
        }

        return View(context)
    }

    fun disablePhysics() {
        newGroup?.physics2d?.disablePhysics()
        //newGroup = null
        gravitySensor?.onStopSensor()
    }

    fun getBackOldViewGroup() {
        clearAllViewsValue()
        recreateOldViewGroup()
        grantParent?.addView(parent)
    }

    private fun clearAllViewsValue() {
        /*for (i in 0..newGroup?.childCount!! - 1) {
            newGroup?.getChildAt(i)?.animate()?.
                    translationY(0f)?.
                    translationX(0f)?.
                    rotation(0f)
        }*/
    }

    private fun recreateOldViewGroup() {
        Log.d("TAG", "---------------------------------------------")
        val parentViews = oldViewGroupList[0].viewsList
        oldViewGroupList.removeAt(0)

        parent?.apply {
            Log.d("TAG", "parentX= " + x + " parentY= " + y)
            removeAllViews()
            parentViews.forEach {
                it.setXY()
                val viewsParent = it.view?.parent as ViewGroup
                viewsParent.removeView(it.view)
                Log.d("TAG", "recreateViewX= " + it.x + " recreateViewY= " + it.y)
                addView(it.view)
            }
            oldViewGroupList.forEach {

                it.onRecreateOldViewGroup()
                addView(it.oldViewGroup, it.oldViewGroup?.layoutParams)
            }
        }
        oldViewGroupList.clear()
        viewGroupsParams = null
        newGroup = null
    }

    fun enablePhysics() {
        gravitySensor?.onResumeSensor()
        newGroup?.physics2d?.enablePhysics()
        newGroup?.invalidate()
    }
}

