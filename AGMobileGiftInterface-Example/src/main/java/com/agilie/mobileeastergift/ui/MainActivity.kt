package com.agilie.mobileeastergift.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.LinearInterpolator
import com.agilie.agmobilegiftinterface.AGMobileGiftInterfaceImpl
import com.agilie.mobileeastergift.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val giftInterfaceImpl = AGMobileGiftInterfaceImpl()

        showLadyBug.setOnClickListener { giftInterfaceImpl.show(this, R.drawable.lady_bug) }
        showFox.setOnClickListener { giftInterfaceImpl.show(this, R.drawable.fox) }
        showRabbit.setOnClickListener { giftInterfaceImpl.show(this, R.drawable.rabbit) }

        gravity.setOnClickListener { startActivity(GravityActivity.getCallingIntent(this)) }


        var animatorSet = AnimatorSet()

        var objectRotateAnimator = ObjectAnimator.ofFloat(shake, "rotation", -5f, 5f)
        objectRotateAnimator.setValues()
        objectRotateAnimator.apply {
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            duration = 70
            interpolator = LinearInterpolator()
        }

        var objectTranslateAnimator = ObjectAnimator.ofFloat(shake, "translate", -5f, 5f)
        objectTranslateAnimator.apply {
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            duration = 70
            interpolator = LinearInterpolator()
        }

        start_shake.setOnClickListener {
            animatorSet.play(objectRotateAnimator).with(objectTranslateAnimator)
            animatorSet.start()
        }


    }
}


