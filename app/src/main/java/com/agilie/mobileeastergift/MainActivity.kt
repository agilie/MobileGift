package com.agilie.mobileeastergift

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.res.Resources.NotFoundException
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import pl.droidsonroids.gif.GifDrawable
import java.io.IOException


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val gifDrawable = GifDrawable(resources, R.drawable.rabbit)
            rabbit_view.setImageDrawable(gifDrawable)
        } catch (e: NotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }


    }


}


