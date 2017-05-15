package com.agilie.agmobilegiftinterface.gravity

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.FrameLayout
import com.agilie.agmobilegiftinterface.gravity.physics.Physics2dViewGroup

/**
 *
 *  Action Flow:
 *  1) Wrap viewGroup with FrameLayout
 *  2) Add PhysicsLayout over the ViewGroup (on the wrapper FrameLayout)
 *  3)
 *
 */
class GravityControllerImpl(val context: Context, val viewGroup: ViewGroup): GravityController {

    init {

    }

    override fun start() {
        val wrapperFrameLayout = getRootFrameLayout(context)
        val physicsLayout = getPhysics2dViewGroup(context)

        wrapViewGroup(viewGroup, wrapperFrameLayout)
        wrapperFrameLayout.addView(physicsLayout)
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
}