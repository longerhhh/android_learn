package com.example.myapplication.widget

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

class BgDrawable : Drawable() {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //    var fourCornerRadius = 0
    var topLeftRadius = 20
    var topRightRadius = 10
    var bottomLeftRadius = 20
    var bottomRightRadius = 20
//    var topRadius = 0
//    var bottomRadius = 0
//    var leftRadius = 0
//    var rightRadius = 0

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(
            bounds.left.toFloat(), bounds.top.toFloat(), bounds.right.toFloat(),
            bounds.bottom.toFloat(), topLeftRadius * 2f, topRightRadius * 2f, paint
        )
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return when {
            alpha > 0 -> PixelFormat.TRANSPARENT
            alpha in 1..254 -> PixelFormat.TRANSLUCENT
            alpha == 255 && topLeftRadius and topRightRadius and bottomLeftRadius and bottomRightRadius == 0 -> PixelFormat.OPAQUE
            else -> PixelFormat.UNKNOWN
        }
    }
}