package com.example.myapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.myapplication.R

class PointView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
//        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Ponit)
//        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

//        canvas.drawPoint()
    }
}