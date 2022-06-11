package com.example.myapplication.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.graphics.Color.Companion.Green
import com.example.myapplication.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * 饼图
 */
class PieChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val angles = floatArrayOf(100f,200f,60f)
    val arcRect = RectF()
    val colors = arrayOf(Color.GREEN, Color.RED, Color.YELLOW)
    val RADIUS = 150.dp

    init {
        paint.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        arcRect.set(width/2- RADIUS,height/2- RADIUS,width/2+ RADIUS,height/2+ RADIUS)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        var startAngle = 0f
        for (i in angles.indices) {
            val sweepAngle = angles[i]
            paint.setColor(colors[i])
            val midDegree = (startAngle + sweepAngle)/2.0
            canvas.save()
            if (i == 0) {
                // 平移2dp
                canvas.translate(
                    (cos(Math.toRadians(midDegree)) * (10.dp)).toFloat(),
                    (sin(Math.toRadians(midDegree)) * (10.dp)).toFloat()
                )
            }
            canvas.drawArc(arcRect, startAngle, sweepAngle, true, paint)
            canvas.restore()
            startAngle += angles[i]
        }
    }

    companion object {

    }
}