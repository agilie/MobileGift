package com.agilie.eastergift

import android.content.Context
import android.content.Intent
import com.agilie.eastergift.GiftService.Companion.RES_ID
import org.jetbrains.anko.doAsync


class AGMobileEasterGiftInterfaceImpl : AGMobileEasterGiftInterface {

    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        doAsync {
            context.startService(intent)
        }
    }

}
