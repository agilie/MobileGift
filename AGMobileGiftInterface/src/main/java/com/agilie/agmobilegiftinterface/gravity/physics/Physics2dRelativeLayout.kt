package com.agilie.agmobilegiftinterface.gravity.physics

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.RelativeLayout

class Physics2dRelativeLayout : RelativeLayout {

    var physics2d: Physics2d? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        physics2d?.onSizeChanged(w, h)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        physics2d?.onLayout()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        physics2d?.updateWorldEntity()
    }

    override fun generateLayoutParams(attrs: AttributeSet): RelativeLayout.LayoutParams {
        return RelativeLayout.LayoutParams(context, attrs)
    }

    private fun init() {
        setWillNotDraw(false)
        physics2d = Physics2d(this)
    }
}