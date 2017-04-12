package com.agilie.mobileeastergift

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilie.eastergift.AGMobileEasterGiftInterfaceImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aglMobile = AGMobileEasterGiftInterfaceImpl()

        showBird.setOnClickListener {
            aglMobile.show(this, R.drawable.bird)
        }

        showFox.setOnClickListener {
            aglMobile.show(this, R.drawable.fox)
        }

        showRabbit.setOnClickListener {
            aglMobile.show(this, R.drawable.rabbit)
        }

    }

}


