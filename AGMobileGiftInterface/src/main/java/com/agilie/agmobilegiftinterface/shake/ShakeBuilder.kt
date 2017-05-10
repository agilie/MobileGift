package com.agilie.agmobilegiftinterface.shake

import android.R
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup
import java.util.*

class ShakeBuilder() {

    lateinit var view: View
        private set

    var activity: Activity? = null
    val animatorSet = AnimatorSet()
    val TRANSLATION_X = "translationX"
    val TRANSLATION_Y = "translationY"
    val TRANSLATION_Z = "translationZ"

    private var mDuration: Long? = 1000
    private var translation: String? = "translationX"


    private constructor (builder: Builder) : this() {
        view = builder.view
        activity = builder.activity
        mDuration = builder.mDuration
        translation = builder.translation
    }

    fun shakeMyView() {
        clear(view)
        shake()
    }

    fun shakeMyActivity() {
        val viewsList = getAllChildren(activity?.window?.decorView?.findViewById(R.id.content))
        for (view in viewsList) {
            clear(view)
            this.view = view
            mDuration = getRandomDuration()
            setRandomTranslation()
            shake()
        }
    }

    private fun getRandomDuration() = (1L + ((Random().nextDouble() * (5000L - 1L)))).toLong()
    private fun getRandomFloat() = Random().nextFloat() * 200 - 100

    private fun setRandomTranslation() {
        when (Random().nextInt(3)) {
            0 -> translation = TRANSLATION_Z
            1 -> translation = TRANSLATION_X
            2 -> translation = TRANSLATION_Y
        }
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

    private fun shake() {
        ViewCompat.setPivotX(view, view.measuredWidth / 2.0f)
        ViewCompat.setPivotY(view, view.measuredHeight / 2.0f)

        when (translation) {
            TRANSLATION_X, TRANSLATION_Y, TRANSLATION_Z -> Unit
            else -> translation = TRANSLATION_X
        }

        with(animatorSet) {
            playTogether(
                    ObjectAnimator.ofFloat(view, translation, 0f, getRandomFloat(), getRandomFloat(),
                            getRandomFloat(), getRandomFloat(), getRandomFloat(), getRandomFloat(),
                            getRandomFloat(), getRandomFloat(), 0f)
            )
            duration = mDuration!!
            start()
        }
    }

    private fun clear(view: View) {
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


    class Builder(val view: View) {

        constructor(activity: Activity, view: View) : this(view) {
            this.activity = activity
        }

        var activity: Activity? = null
            private set

        var mDuration: Long? = null
            private set
        var translation: String? = null
            private set

        fun setActivity(activity: Activity): Builder {
            this.activity = activity
            return this
        }

        fun setDuration(duration: Long): Builder {
            this.mDuration = duration
            return this
        }

        fun setTranslation(translation: String): Builder {
            this.translation = translation
            return this
        }

        fun build() = ShakeBuilder(this)

    }
}