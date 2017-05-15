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
    private var childCount: Int = 0
    private var paramsMap: HashMap<ViewGroup, ViewGroupParams> = HashMap()

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
        if (!oldViewGroupList.isEmpty()) {
            return
        }
        childCount = viewGroup.childCount
        contentFrame = FrameLayout(context)
        val frameParam = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        contentFrame?.layoutParams = frameParam

        parent = viewGroup
        grantParent = viewGroup.parent as ViewGroup
        grantParent?.removeView(viewGroup)

        newGroup = Physics2dViewGroup(context)


        //val viewsList = getAllChildren(viewGroup)
        //removeAllChildrenFromViewGroup(viewsList)
        //cloneSimpleView(context, viewsList)

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
            //getOnScreenPosition(viewGroup)
            //Log.d("LOCATION", "viewX= " + viewGroup.x + " viewY= " + viewGroup.y)
            viewGroup.x += oldGroupViewX
            viewGroup.y += oldGroupViewY
            newGroup?.addView(viewGroup)
            viewGroupsParams?.viewsList?.add(viewGroup)

            return viewGroup
        }
        oldGroupViewX = viewGroup.x
        oldGroupViewY = viewGroup.y

        //getOnScreenPosition(viewGroup)
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
            transX = viewGroup.translationX
            transY = viewGroup.translationY
            layoutParams = viewGroup.layoutParams
        }

        viewGroupsParams?.let { oldViewGroupList.add(it) }

        while (viewGroup.childCount > 0) {
            val childView = viewGroup.getChildAt(0)
            viewGroup.removeView(childView)
            initCustomViewGroup(context, childView as View)
        }

        return View(context)
    }

    fun disablePhysics() {
        newGroup?.physics2d?.disablePhysics()
        gravitySensor?.onStopSensor()
    }

    fun getBackOldViewGroup() {
        recreateOldViewGroup()

    }

    private fun recreateOldViewGroup() {
        if (oldViewGroupList.isEmpty()) {
            return
        }
        Log.d("TAG", "---------------------------------------------")
        val parentViews = oldViewGroupList[0].viewsList
        oldViewGroupList.removeAt(0)

        parent?.apply {
            Log.d("TAG", "parentX= " + x + " parentY= " + y)
            removeAllViews()
            parentViews.forEach {
                it.animate()?.rotation(0f)?.translationX(0f)?.translationY(0f)
                val viewsParent = it.parent as ViewGroup
                viewsParent.removeView(it)
                addView(it)
            }
            oldViewGroupList.forEach {

                it.onRecreateOldViewGroup()
                addView(it.oldViewGroup)
            }
        }
        grantParent?.addView(parent)

        oldViewGroupList.clear()
        viewGroupsParams = null
        newGroup = null
        contentFrame = null
    }

    fun enablePhysics() {
        gravitySensor?.onResumeSensor()
        newGroup?.physics2d?.enablePhysics()
        newGroup?.invalidate()
    }


    private fun getAllChildren(view: View?): List<View> {

        if (view !is ViewGroup) {

            val viewArrayList = ArrayList<View>()
            view?.let { viewArrayList.add(it) }
            return viewArrayList
        }

        val result = ArrayList<View>()

        (0..view.childCount - 1)
                .map { view.getChildAt(it) }
                .forEach {
                    val viewArrayList = ArrayList<View>()
                    viewArrayList.add(view)
                    viewArrayList.addAll(getAllChildren(it))
                    result.addAll(viewArrayList)
                }

        return result.distinct()
    }

    private fun removeAllChildrenFromViewGroup(views: List<View>) {

        views.forEach {
            if (it is ViewGroup) {
                val viewGroupParams = ViewGroupParams()
                viewGroupParams.apply {
                    groupViewBottom = it.bottom
                    groupViewLeft = it.left
                    groupViewRight = it.right
                    groupViewTop = it.top
                    groupViewX = it.x
                    groupViewY = it.y
                    transX = it.translationX
                    transY = it.translationY
                }
                paramsMap.put(it, viewGroupParams)
            } else {
                val childParent = it.parent as ViewGroup
                childParent.removeView(it)
                newGroup?.addView(it, it.layoutParams)
            }
        }
        paramsMap.forEach { t, u ->
            u.setViewGroupParam(t)
        }
    }


    private fun cloneSimpleView(context: Context, views: List<View>) {
        views.forEach {
            if (it !is ViewGroup) {
                it.visibility = View.GONE
//                val cloneView = it.javaClass.constructors[0].newInstance(context)
                //View(context)
                //cloneView.layoutParams = it.layoutParams
                //cloneView.visibility = View.VISIBLE
                //newGroup?.addView(cloneView)
            }
        }
    }
}

