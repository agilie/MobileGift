package com.agilie.agmobilegiftinterface.gravity

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.FrameLayout
import com.agilie.agmobilegiftinterface.gravity.physics.Physics2dViewGroup


/**
 *
 *  Action Flow:
 *  1) Wrap viewGroup with FrameLayout
 *  2) Add PhysicsLayout over the ViewGroup (on the wrapper FrameLayout)
 *  3) Save all views from all viewGroups
 *  4) Add all views from all viewGroups to PhysicsLayout
 *  5) Set proper coordinates for views added to the PhysicsLayout
 *
 */
class GravityControllerImpl(val context: Context, val viewGroup: ViewGroup) : GravityController {

    private var gravitySensor: GravitySensorListener? = null

    var wrapperFrameLayout: FrameLayout? = null
    var physicsLayout: Physics2dViewGroup? = null
    val viewsHashMap = HashMap<View, ViewInfo>()

    var gravityEnabled = false

    init {

    }

    class ViewInfo {
        var initialParent: ViewParent? = null
        var initialLayoutParams: ViewGroup.LayoutParams? = null
        var initialX = 0.0f
        var initialY = 0.0f
        var initialRotation = 0.0f

        var globalCoordinates: IntArray? = null
    }

    override fun start() {
        if (gravityEnabled) return

        val viewGroupCoordinates = IntArray(2)
        viewGroup.getLocationInWindow(viewGroupCoordinates)

        wrapperFrameLayout = getRootFrameLayout(context)
        physicsLayout = getPhysics2dViewGroup(context)

        wrapViewGroup(viewGroup, wrapperFrameLayout!!)
        wrapperFrameLayout?.addView(physicsLayout)

        val views = getViewsFromAllViewGroup(viewGroup)

        viewsHashMap.clear()

        // save initial info
        views.forEach { view ->
            val coordinates = IntArray(2)
            view.getLocationInWindow(coordinates)

            val viewInfo = ViewInfo()
            viewInfo.initialParent = view.parent
            viewInfo.initialLayoutParams = view.layoutParams
            viewInfo.initialX = view.x
            viewInfo.initialY = view.y
            viewInfo.initialRotation = view.rotation
            viewInfo.globalCoordinates = coordinates

            viewsHashMap.put(view, viewInfo)
        }

        wrapChildViews(views, physicsLayout!!)

        for ((view, viewInfo) in viewsHashMap) {
            view.x = viewInfo.globalCoordinates!![0].toFloat() - viewGroupCoordinates[0]
            view.y = viewInfo.globalCoordinates!![1].toFloat() - viewGroupCoordinates[1]
        }

        startSensorListener(context, physicsLayout!!)

        gravityEnabled = true
    }

    override fun stop() {
        if (!gravityEnabled) return

        stopSensorListener(physicsLayout!!)

        // return back our views to initial Parents
        for ((view, viewInfo) in viewsHashMap) {
            removeSelfFromParent(view)
            (viewInfo.initialParent as ViewGroup).addView(view, viewInfo.initialLayoutParams)

            view.apply {
                animate()
                        .x(viewInfo.initialX)
                        .y(viewInfo.initialY)
                        .rotation(viewInfo.initialRotation)
            }
        }

        // TODO: remove root wrapper layout

        // TODO: remove physics layout

        gravityEnabled = false
    }

    /* Private helpers */

    private fun wrapViewGroup(viewGroup: ViewGroup, wrapperViewGroup: ViewGroup) {
        // remove self from parent
        val viewGroupParent = getParentAndRemoveSelf(viewGroup)

        viewGroupParent.addView(wrapperViewGroup, 0)
        wrapperViewGroup.addView(viewGroup)
    }

    private fun getParentAndRemoveSelf(view: View): ViewGroup {
        val viewGroupParent = view.parent as ViewGroup
        viewGroupParent.removeView(view)
        return viewGroupParent
    }

    private fun removeSelfFromParent(view: View) {
        val viewGroupParent = view.parent as ViewGroup
        viewGroupParent.removeView(view)
    }

    private fun getRootFrameLayout(context: Context): FrameLayout {
        val rootFrameLayout = FrameLayout(context)
        rootFrameLayout.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        return rootFrameLayout
    }

    private fun getPhysics2dViewGroup(context: Context): Physics2dViewGroup {
        val physics2dViewGroup = Physics2dViewGroup(context)
        return physics2dViewGroup
    }

    private fun getViewsFromAllViewGroup(view: View): List<View> {
        if (view !is ViewGroup) {
            val list = ArrayList<View>()
            view.let { list.add(it) }
            return list
        }

        val viewsList = ArrayList<View>()

        (0..view.childCount - 1)
                .map { view.getChildAt(it) }
                .forEach {
                    val list = ArrayList<View>()
                    list.addAll(getViewsFromAllViewGroup(it))
                    viewsList.addAll(list)
                }

        return viewsList
    }

    private fun wrapChildViews(views: List<View>, wrapperViewGroup: ViewGroup) {
        views.forEach { view ->
            removeSelfFromParent(view)
            wrapperViewGroup.addView(view)
        }
    }

    private fun startSensorListener(context: Context, viewGroup: Physics2dViewGroup) {
        if (gravitySensor == null) {
            gravitySensor = GravitySensorListener(context)
        }
        gravitySensor?.onResumeSensor()
        gravitySensor?.gravityListener = (object : GravitySensorListener.onGravityListener {
            override fun onGravity(x: Float, y: Float) {
                viewGroup.physics2d?.onStartGravity(x, y)
            }
        })
    }

    private fun stopSensorListener(viewGroup: Physics2dViewGroup) {
        gravitySensor?.onStopSensor()
        viewGroup.physics2d?.disablePhysics()
    }
}