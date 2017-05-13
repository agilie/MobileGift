package com.agilie.agmobilegiftinterface.gravity.physics.view

import android.util.Log
import android.view.View
import android.view.ViewGroup

class ViewGroupParams {

    var groupViewBottom: Int = 0
    var groupViewLeft: Int = 0
    var groupViewRight: Int = 0
    var groupViewTop: Int = 0
    var groupViewX: Float = 0f
    var groupViewY: Float = 0f
    var oldGroupViewX: Float = 0f
    var oldGroupViewY: Float = 0f
    var oldViewGroup: ViewGroup? = null
    var viewsList = ArrayList<ViewParams>()

    fun setViewGroupParam() {

        oldViewGroup?.apply {
            top = groupViewTop
            bottom = groupViewBottom
            right = groupViewRight
            left = groupViewLeft
            oldGroupViewX = groupViewX
            oldGroupViewY = groupViewY
            x += groupViewX
            y += groupViewY
        }

        val params = oldViewGroup?.layoutParams
        params?.apply {
            height = groupViewBottom - groupViewTop
            width = groupViewRight - groupViewLeft
        }

        oldViewGroup?.layoutParams = params
    }

    fun onRecreateOldViewGroup() {
        Log.d("TAG", "recreateViewGroupX= " + oldViewGroup?.x + " recreateViewGroupY= " + oldViewGroup?.y)
        removeParent(oldViewGroup as View)
        viewsList.forEach {
            removeParent(it.view)
            it.setXY()
            Log.d("TAG", "recreateViewX= " + it.x + " recreateViewY= " + it.y)
            it.view?.animate()?.rotation(0f)
            oldViewGroup?.addView(it.view)
        }
    }


    private fun removeParent(viewGroup: View?) {
        val parent = viewGroup?.parent as ViewGroup?
        parent?.removeAllViews()
    }
}

