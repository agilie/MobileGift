package com.agilie.eastergift

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.view.WindowManager
import android.graphics.PixelFormat
import android.view.Gravity
import pl.droidsonroids.gif.GifImageView
import org.jetbrains.anko.windowManager


class GiftService : Service() {


    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()

        var windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val gifImageView = GifImageView(applicationContext)
        //val gifView = (applicationContext as Activity).findViewById(R.id.text) as GifImageView

        //gifImageView.setImageBitmap(bitmap)


        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        params.gravity = Gravity.TOP or Gravity.LEFT
        params.x = 0
        params.y = 100
        windowManager.addView(gifImageView, params)


    }

    override fun onDestroy() {
        super.onDestroy()
        //   windowManager.removeView(gifImageView)
    }
}