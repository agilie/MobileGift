package com.agilie.agmobilegiftinterface

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.agilie.agmobilegiftinterface.animation.GiftService
import com.agilie.agmobilegiftinterface.animation.GiftService.Companion.RES_ID
import com.agilie.agmobilegiftinterface.gravity.GravityController
import com.agilie.agmobilegiftinterface.gravity.GravityControllerImpl
import com.agilie.agmobilegiftinterface.gravity.GravitySensorListener
import com.agilie.agmobilegiftinterface.gravity.physics.Physics2dViewGroup
import com.agilie.agmobilegiftinterface.gravity.physics.view.ViewGroupParams
import com.agilie.agmobilegiftinterface.shake.ShakeBuilder


class AGMobileGiftInterfaceImpl : AGMobileGiftInterface {

    var gravityController: GravityController? = null

    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        Thread().run {
            context.startService(intent)
        }
    }

    override fun shake(view: View) = ShakeBuilder.Builder(view)

    override fun startGravity(context: Context, viewGroup: ViewGroup) {
        gravityController = GravityControllerImpl(context, viewGroup)
        gravityController?.start()
    }

    override fun stopGravity() {
        gravityController?.stop()
    }
}

