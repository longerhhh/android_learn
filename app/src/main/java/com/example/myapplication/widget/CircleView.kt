package com.example.myapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.myapplication.dp
import java.lang.Math.min

class CircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val s = (defaultRadius + padding) * 2
        val w = resolveSize(s.toInt(), widthMeasureSpec)
        val h = resolveSize(s.toInt(), heightMeasureSpec)
        setMeasuredDimension(w,h)
    }

    private val radius
        get() = (kotlin.math.min(width, height)-padding*2)/2
    val defaultRadius = 100.dp
    val padding = 10.dp

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        canvas.drawCircle(width/2f, height/2f, radius, paint)
    }
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!changed) return


    }
}