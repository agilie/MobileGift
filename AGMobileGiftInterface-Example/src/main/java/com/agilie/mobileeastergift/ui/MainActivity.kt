package com.agilie.mobileeastergift.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilie.agmobilegiftinterface.AGMobileGiftInterfaceImpl
import com.agilie.mobileeastergift.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layout = physics_layout

        val giftInterfaceImpl = AGMobileGiftInterfaceImpl()
        /*showLadyBug.setOnClickListener { giftInterfaceImpl.show(this, R.drawable.lady_bug) }
        showFox.setOnClickListener { giftInterfaceImpl.show(this, R.drawable.fox) }
        showRabbit.setOnClickListener { giftInterfaceImpl.show(this, R.drawable.rabbit) }*/

        /*var shakeBuilder = ShakeBuilder.Builder(showLadyBug)

        var shakeBuilder = ShakeBuilder.Builder(showLadyBug)
                .setDuration(1000)
                .setTranslation("translationY")
                .setActivity(this)
                .build()
        //    button.setOnClickListener { shakeBuilder.shakeMyActivity() }*/
        button.setOnClickListener { giftInterfaceImpl.startGravity(this, layout) }

        disable_physics.setOnClickListener {
            giftInterfaceImpl.stopGravity()
        }

        secondActivityNavigation.setOnClickListener { startActivity(SecondTestActivity.getCallingIntent(this)) }

    }
}


