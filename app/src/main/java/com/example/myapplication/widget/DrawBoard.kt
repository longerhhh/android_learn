package com.example.myapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.FloatRange
import com.example.myapplication.dp

/**
 * 多指触摸滑动的视图
 */
class DrawBoard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val TAG = this.javaClass.simpleName

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val paths = mutableListOf<Path>()

    @FloatRange(from = 0.0)
    var cx = 0f

    @FloatRange(from = 0.0)
    var cy = 0f

    init {
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2.dp
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        cx = width / 2f
        cy = height / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        for (path in paths) {
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                for (i in 0 until event.pointerCount) {
                    if (paths.lastIndex < i) {
                        paths.add(Path())
                    } else {
                        paths[i].reset()
                    }
                    paths[i].moveTo(event.getX(i), event.getY(i))
                }
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    paths[i].lineTo(event.getX(i), event.getY(i))
                }
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
//                for (path in paths) {
//                    path.reset()
//                }
            }
        }
        return true
    }
}