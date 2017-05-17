package com.agilie.mobileeastergift.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
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



        var anim = AnimationUtils.loadAnimation(this, R.anim.shake)


        start_shake.setOnClickListener { shake.startAnimation(anim) }


    }
}


