package com.agilie.agmobilegiftinterface

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.agilie.agmobilegiftinterface.GiftService.Companion.RES_ID


class AGMobileGiftInterfaceImpl : AGMobileGiftInterface {

    override fun shake(activity: Activity) {
        //val shakeView = ShakeView(activity)
        //shakeView.shakeMe()
       // viewGroup?.apply { shakeView.test(viewGroup) }
    }


    override fun show(context: Context, id: Int) {
        val intent = Intent(context, GiftService::class.java).putExtra(RES_ID, id)
        Thread().run {
            context.startService(intent)
        }
    }

}
