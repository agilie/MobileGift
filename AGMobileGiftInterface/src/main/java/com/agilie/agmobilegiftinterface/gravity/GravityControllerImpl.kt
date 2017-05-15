package com.agilie.agmobilegiftinterface.gravity

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.agilie.agmobilegiftinterface.gravity.physics.Physics2dViewGroup


/**
 *
 *  Action Flow:
 *  1) Wrap viewGroup with FrameLayout
 *  2) Add PhysicsLayout over the ViewGroup (on the wrapper FrameLayout)
 *  3) Save all views from all viewGroups
 *  4) Add all views from all viewGroups to PhysicsLayout
 *
 */
class GravityControllerImpl(val context: Context, val viewGroup: ViewGroup) : GravityController {

    init {

    }

    override fun start() {
        val wrapperFrameLayout = getRootFrameLayout(context)
        val physicsLayout = getPhysics2dViewGroup(context)

        wrapViewGroup(viewGroup, wrapperFrameLayout)
        wrapperFrameLayout.addView(physicsLayout)

        val viewHashMap = getViewsFromAllViewGroup(viewGroup)

        wrapChildView(viewHashMap, physicsLayout)
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /* Private helpers */

    private fun wrapViewGroup(viewGroup: ViewGroup, wrapperViewGroup: ViewGroup) {
        // remove self from parent
        val viewGroupParent = getParentAndRemoveSelf(viewGroup)

        viewGroupParent.addView(wrapperViewGroup, 0)
        wrapperViewGroup.addView(viewGroup)
    }

    private fun getParentAndRemoveSelf(viewGroup: ViewGroup): ViewGroup {
        val viewGroupParent = viewGroup.parent as ViewGroup
        viewGroupParent.removeView(viewGroup)
        return viewGroupParent
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

    private fun getViewsFromAllViewGroup(view: View): HashMap<View, ViewGroup.LayoutParams> {
        if (view !is ViewGroup) {
            val hashMap = HashMap<View, ViewGroup.LayoutParams>()
            view.let { hashMap.put(it, it.layoutParams) }
            return hashMap
        }

        val viewsHashMap = HashMap<View, ViewGroup.LayoutParams>()

        (0..view.childCount - 1)
                .map { view.getChildAt(it) }
                .forEach {
                    val hashMap = HashMap<View, ViewGroup.LayoutParams>()
                    hashMap.putAll(getViewsFromAllViewGroup(it))
                    viewsHashMap.putAll(hashMap)
                }

        return viewsHashMap
    }

    private fun wrapChildView(viewHashMap: HashMap<View, ViewGroup.LayoutParams>,
                              wrapperViewGroup: ViewGroup) {
        for ((key) in viewHashMap) {
            val childParent = key.parent as ViewGroup
            childParent.removeView(key)
            wrapperViewGroup.addView(key)
        }
    }
}