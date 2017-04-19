package com.agilie.agmobilegiftinterface.animation

import android.R
import android.app.Activity
import android.view.View
import android.view.ViewGroup


class ShakeView(val activity: Activity) {

    fun shakeMe() {
        val view = activity.window.decorView.findViewById(R.id.content)
        val baseAnimation = BaseAnimation(getAllChildren(view))
        baseAnimation.start()

    }


    private fun getAllChildren(view: View): List<View> {

        if (view !is ViewGroup) {
            val viewArrayList = ArrayList<View>()
            viewArrayList.add(view)
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
}


