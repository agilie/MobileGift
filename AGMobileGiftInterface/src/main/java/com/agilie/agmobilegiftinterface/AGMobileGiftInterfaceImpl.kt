package com.agilie.agmobilegiftinterface

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.agilie.agmobilegiftinterface.animation.GiftService
import com.agilie.agmobilegiftinterface.animation.GiftService.Companion.RES_ID
import com.agilie.agmobilegiftinterface.gravity.GravitySensorListener
import com.agilie.agmobilegiftinterface.gravity.physics.Physics2dRelativeLayout
import com.agilie.agmobilegiftinterface.shake.ShakeBuilder


class AGMobileGiftInterfaceImpl : AGMobileGiftInterface {

    override fun startGravity(context: Context, viewGroup: ViewGroup) {
        when (viewGroup) {
            is Physics2dRelativeLayout -> onStartGravity(context, viewGroup)
            else -> throw Exception("You must use Physics2dRelativeLayout")
        }
    }

    private fun onStartGravity(context: Context, viewGroup: Physics2dRelativeLayout) {
        val gravitySensor = GravitySensorListener(context)
        gravitySensor.onResumeSensor()
        gravitySensor.gravityListener = (object : GravitySensorListener.onGravityListener {
            override fun onGravity(x: Float, y: Float) {
                viewGroup.physics2d?.onStartGravity(x, y)
            }
        })
    }

    override fun shake(view: View) = ShakeBuilder.Builder(view)

    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        Thread().run {
            context.startService(intent)
        }
    }

}
