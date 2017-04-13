package com.agilie.mobileeastergift

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilie.agmobilegiftinterface.AGMobileGiftInterfaceImpl
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val giftInterfaceImpl = AGMobileGiftInterfaceImpl()

        showLadyBug.onClick { giftInterfaceImpl.show(this, R.drawable.lady_bug) }
        showFox.onClick { giftInterfaceImpl.show(this, R.drawable.fox) }
        showRabbit.onClick { giftInterfaceImpl.show(this, R.drawable.rabbit) }
    }

}


