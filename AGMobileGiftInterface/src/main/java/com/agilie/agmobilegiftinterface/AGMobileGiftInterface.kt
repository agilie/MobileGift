package com.agilie.agmobilegiftinterface

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.agilie.agmobilegiftinterface.shake.ShakeBuilder


interface AGMobileGiftInterface {

    fun show(context: Context, id: Int)

    fun shake(view: View): ShakeBuilder.Builder

    fun startGravity(context: Context, viewGroup: ViewGroup)
}