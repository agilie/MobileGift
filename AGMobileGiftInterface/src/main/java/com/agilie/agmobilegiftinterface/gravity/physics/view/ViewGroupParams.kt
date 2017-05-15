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
    var transX = 0f
    var transY = 0f
    var layoutParams: ViewGroup.LayoutParams? = null
    var oldViewGroup: ViewGroup? = null
    var viewsList = ArrayList<View>()

    fun setViewGroupParam() {

        //oldViewGroup?.layoutParams = layoutParams

        oldViewGroup?.apply {
            top = groupViewTop
            bottom = groupViewBottom
            right = groupViewRight
            left = groupViewLeft
            oldGroupViewX = groupViewX
            oldGroupViewY = groupViewY
            //x += groupViewX
            //y += groupViewY
            Log.d("TAG", "setViewGroupX= " + x + " setViewGroupY= " + y)
        }

        val params = oldViewGroup?.layoutParams
        params?.apply {
            height = groupViewBottom - groupViewTop
            width = groupViewRight - groupViewLeft
        }

        oldViewGroup?.layoutParams = params
        oldViewGroup?.animate()?.translationX(transX)?.translationY(transY)
    }

    fun onRecreateOldViewGroup() {


        oldViewGroup?.animate()?.translationX(oldGroupViewX)?.translationY(oldGroupViewY)


        /*oldViewGroup?.x = oldGroupViewX
        oldViewGroup?.y = oldGroupViewY*/

        Log.d("TAG", "recreateViewGroupX= " + oldViewGroup?.x + " recreateViewGroupY= " + oldViewGroup?.y)
        removeParent(oldViewGroup as View)
        viewsList.forEach {
            removeParent(it)
            it.animate()?.rotation(0f)?.translationX(0f)?.translationY(0f)
            oldViewGroup?.addView(it)
        }
    }


    private fun removeParent(viewGroup: View?) {
        val parent = viewGroup?.parent as ViewGroup?
        parent?.removeAllViews()
    }
}

