package com.agilie.agmobilegiftinterface.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v4.view.ViewCompat
import android.view.View

class BaseAnimation(private val viewsList: List<View>) {

    val animatorSet = AnimatorSet()
    val DURATION: Long = 1000

    private fun play() {
        clear()
        for (view in viewsList) {

            ViewCompat.setPivotX(view, view.measuredWidth / 2.0f)
            ViewCompat.setPivotY(view, view.measuredHeight / 2.0f)

            with(animatorSet) {
                playTogether(
                        ObjectAnimator.ofFloat(view, "translationX", 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f))
                duration = DURATION
                start()
            }
        }

    }

    private fun clear() {
        for (view in viewsList) {
            with(view) {
                alpha = 1f
                scaleX = 1f
                scaleY = 1f
                translationX = 1f
                translationY = 1f
                rotation = 0f
                rotationX = 0f
                rotationY = 0f
            }
        }
    }

    fun start() {
        play()
    }


}