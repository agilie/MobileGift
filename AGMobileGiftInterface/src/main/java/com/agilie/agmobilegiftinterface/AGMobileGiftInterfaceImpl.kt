package com.agilie.agmobilegiftinterface

import android.content.Context
import android.content.Intent
import android.view.View
import com.agilie.agmobilegiftinterface.animation.GiftService
import com.agilie.agmobilegiftinterface.animation.GiftService.Companion.RES_ID
import com.agilie.agmobilegiftinterface.shake.ShakeBuilder


class AGMobileGiftInterfaceImpl : AGMobileGiftInterface {

    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        Thread().run {
            context.startService(intent)
        }
    }

    override fun shake(view: View) = ShakeBuilder.Builder(view)

}

