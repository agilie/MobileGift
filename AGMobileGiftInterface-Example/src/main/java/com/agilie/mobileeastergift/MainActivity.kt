package com.agilie.mobileeastergift

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilie.agmobilegiftinterface.AGMobileGiftInterfaceImpl
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

        //shake
        /*var shakeBuilder = ShakeBuilder.Builder(showLadyBug)
                .setDuration(1000)
                .setTranslation("translationY")
                .setActivity(this)
                .build()
        //    button.setOnClickListener { shakeBuilder.shakeMyActivity() }*/
        button.setOnClickListener { giftInterfaceImpl.startGravity(this, layout) }

        disable_physics.setOnClickListener {
            giftInterfaceImpl.stopGravity()
        }

//        enable_physics.setOnClickListener {
//            giftInterfaceImpl.enablePhysics()
//        }


        revert.setOnClickListener {
            giftInterfaceImpl.getBackOldViewGroup()
        }

        //physics
        //giftInterfaceImpl.startGravity(this, layout)

        /* enable_physics.setOnCheckedChangeListener { buttonView, isChecked ->
             if (isChecked) {
                 layout.physics2d?.enablePhysics(true)
                 giftInterfaceImpl.startGravity(this, layout)
             } else {
                 layout.physics2d?.enablePhysics(false)
                 for (i in 0..layout.childCount - 1) {
                     layout.getChildAt(i)
                             .animate().translationY(0f).translationX(0f).rotation(0f)
                 }
             }
         }*/

    }
}


