package com.agilie.agmobilegiftinterface

import android.app.Activity
import android.content.Context


interface AGMobileGiftInterface {
    fun show(context: Context, id: Int)

    fun shake(activity: Activity)


}