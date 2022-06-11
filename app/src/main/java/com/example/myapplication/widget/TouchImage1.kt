package com.example.myapplication.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.FloatRange
import com.example.myapplication.dp
import com.example.myapplication.getAvatar

/**
 * 多指触摸滑动的视图
 */
class TouchImage1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val TAG = this.javaClass.simpleName

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bmpW = 200.dp
    val bmp: Bitmap = getAvatar(bmpW)
    val h2 = bmp.height / 2f
    val w2 = bmp.width / 2f

    var offsetX = 0f
    var offsetY = 0f

    @FloatRange(from = 0.0)
    var cx = 0f

    @FloatRange(from = 0.0)
    var cy = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        cx = width / 2f
        cy = height / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        canvas.translate(offsetX, offsetY)
        canvas.drawBitmap(bmp, cx - w2, cy - h2, paint)
    }

    // 按下时的x坐标
    var downX = 0f

    // 按下时的y坐标
    var downY = 0f

    var originalX = 0f
    var originalY = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false

        var sumX = 0f
        var sumY = 0f
        val isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        var pointerCount = event.pointerCount
        for (i in 0 until pointerCount) {
            if (isPointerUp && i == event.actionIndex) {
                continue
            }
            sumX += event.getX(i)
            sumY += event.getY(i)
        }
        if (isPointerUp) {
            pointerCount -= 1
        }
        val focusX = sumX / pointerCount
        val focusY = sumY / pointerCount

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                downX = focusX
                downY = focusY
                originalX = offsetX
                originalY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + originalX
                offsetY = focusY - downY + originalY
                invalidate()
            }
        }

        return true
    }
}