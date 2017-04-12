package com.agilie.eastergift

import android.content.Context
import android.content.Intent
import org.jetbrains.anko.doAsync


class AGMobileEasterGiftInterfaceImpl : AGMobileEasterGiftInterface {

    companion object {
        val RES_ID = "RES_ID"
    }

    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        doAsync {
            context.startService(intent)
        }
    }

}
