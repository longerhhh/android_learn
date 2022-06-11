package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import kotlin.math.max
import kotlin.math.min

/**
 * 方形imageView
 */
class SquareImageView(context: Context, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = min(measuredWidth, measuredHeight)
//        val ws = MeasureSpec.getSize(measuredWidth)
//        val hs = MeasureSpec.getSize(measuredHeight)
//        val s = min(ws, hs)
//        setMeasuredDimension(s, s)
////        val w = resolveSize(measuredWidth, widthMeasureSpec)
////        val h = resolveSize(measuredHeight, heightMeasureSpec)
        setMeasuredDimension(size,size)
    }
}