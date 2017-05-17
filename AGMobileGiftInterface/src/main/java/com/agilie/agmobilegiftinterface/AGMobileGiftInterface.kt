package com.agilie.agmobilegiftinterface

import android.content.Context
import android.view.View
import com.agilie.agmobilegiftinterface.shake.ShakeBuilder


interface AGMobileGiftInterface {

    fun show(context: Context, id: Int)

    fun shake(view: View): ShakeBuilder.Builder

}