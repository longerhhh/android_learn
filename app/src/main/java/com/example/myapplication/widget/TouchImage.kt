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

class TouchImage @JvmOverloads constructor(
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

    // 追踪的手指
    var trackingPointerId = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = event.getPointerId(event.actionIndex)
                downX = event.getX(event.actionIndex)
                downY = event.getY(event.actionIndex)
            }
            MotionEvent.ACTION_MOVE -> {
                val findPointerIndex = event.findPointerIndex(trackingPointerId)
                offsetX = event.getX(findPointerIndex) - downX
                offsetY = event.getY(findPointerIndex) - downY
                invalidate()
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val findPointerIndex = event.findPointerIndex(trackingPointerId)
                downX += event.getX(event.actionIndex) - event.getX(findPointerIndex)
                downY += event.getY(event.actionIndex) - event.getY(findPointerIndex)
                trackingPointerId = event.getPointerId(event.actionIndex)
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val pointerId = event.getPointerId(event.actionIndex)
                if (pointerId == trackingPointerId) {
                    val pointIndex = event.findPointerIndex(trackingPointerId)
                    trackingPointerId =
                        event.pointerCount - if (pointIndex == event.pointerCount - 1) 2 else 1
                }
            }
        }

        return true
    }
}