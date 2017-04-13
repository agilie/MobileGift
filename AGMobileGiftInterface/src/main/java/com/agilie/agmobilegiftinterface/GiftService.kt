package com.agilie.agmobilegiftinterface

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
import android.view.WindowManager.LayoutParams.TYPE_TOAST
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView


class GiftService : Service() {

    companion object {
        val RES_ID = "RES_ID"
    }

    private var windowManager: WindowManager? = null
    private var gifImageView: GifImageView? = null
    private var resourceId: Int = 0

    override fun onCreate() {
        super.onCreate()

        init()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        resourceId = intent?.getIntExtra(RES_ID, 0)!!
        try {
            setGifAnimation(resourceId)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        removeGifView()

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun init() {
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        gifImageView = GifImageView(applicationContext)
    }

    private fun setGifAnimation(gifResId: Int) {
        val gif = GifDrawable(resources, gifResId)
        gif.addAnimationListener { stopSelf() }
        gifImageView?.setImageDrawable(gif)

        with(WindowManager.LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT,
                TYPE_TOAST,
                FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)) {

            gravity = Gravity.TOP or Gravity.LEFT
            x = 0
            y = 0

            gifImageView?.windowToken?.apply { removeGifView() } ?: windowManager?.addView(gifImageView, this)
        }
    }

    private fun removeGifView() {
        windowManager?.removeView(gifImageView)
    }

}


