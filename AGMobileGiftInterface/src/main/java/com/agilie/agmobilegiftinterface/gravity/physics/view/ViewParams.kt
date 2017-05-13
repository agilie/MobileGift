package com.agilie.agmobilegiftinterface.gravity.physics.view

import android.view.View

class ViewParams {
    var x = 0f
    var y = 0f
    var view: View? = null

    fun setXY(){
        view?.x=x
        view?.y=y
    }
}