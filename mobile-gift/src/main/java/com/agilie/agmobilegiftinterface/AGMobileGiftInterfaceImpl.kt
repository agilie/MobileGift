package com.agilie.agmobilegiftinterface

import android.content.Context
import android.content.Intent
import com.agilie.agmobilegiftinterface.GiftService.Companion.RES_ID


class AGMobileGiftInterfaceImpl : AGMobileGiftInterface {

    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        Thread().run {
            context.startService(intent)
        }
    }

}
