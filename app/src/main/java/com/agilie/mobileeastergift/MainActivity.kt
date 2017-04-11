package com.agilie.mobileeastergift

import android.app.WallpaperManager
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.agilie.eastergift.AGMobileEasterGiftInterfaceImpl
import kotlinx.android.synthetic.main.activity_main.*
import pl.droidsonroids.gif.GifDrawable
import java.io.IOException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val aglMobile = AGMobileEasterGiftInterfaceImpl()
        aglMobile.show(this)


        showRabbit.setOnClickListener {
            // wallpaperManager.setResource(R.drawable.my_image)
            try {
                val gifDrawable = GifDrawable(resources, R.drawable.rabbit)
                rabbitView.setImageDrawable(gifDrawable)
            } catch (e: Resources.NotFoundException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }


    }


}


