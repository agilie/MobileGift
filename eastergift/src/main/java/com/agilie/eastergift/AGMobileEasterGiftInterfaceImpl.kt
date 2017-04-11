package com.agilie.eastergift

import android.content.Context
import android.content.Intent


class AGMobileEasterGiftInterfaceImpl() : AGMobileEasterGiftInterface {


    override fun show(context: Context) {
        context.startService(Intent(context, GiftService::class.java))
    }
}
