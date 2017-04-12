package com.agilie.eastergift

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.view.WindowManager
import android.graphics.PixelFormat
import android.util.Log
import android.view.Gravity
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView


class GiftService : Service() {


    companion object {
        val RES_ID = "RES_ID"
    }

    private var windowManager: WindowManager? = null
    private var gifImageView: GifImageView? = null
    private var resourceId: Int = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        resourceId = intent?.getIntExtra(RES_ID, 0)!!

        setGifAnimation(resourceId)

        return super.onStartCommand(intent, flags, startId)
    }


    override fun onCreate() {
        super.onCreate()
        init()

    }


    private fun init() {
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        gifImageView = GifImageView(applicationContext)
    }

    private fun setGifAnimation(gifResId: Int) {
        val gif = GifDrawable(resources, gifResId)
        gif.addAnimationListener { stopSelf() }

        gifImageView?.setImageDrawable(gif)

        val params = WindowManager.LayoutParams(

                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        params.gravity = Gravity.TOP or Gravity.LEFT
        params.x = 0
        params.y = 0

        if (gifImageView?.windowToken != null) {
            removeGifView()
        } else {
            windowManager?.addView(gifImageView, params)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeGifView()
    }


    private fun removeGifView() {
        windowManager?.removeView(gifImageView)
    }


}


