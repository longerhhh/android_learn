package com.example.myapplication.widget

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.myapplication.dp
import java.util.jar.Attributes
import kotlin.math.cos
import kotlin.math.sin

/**
 * 仪表盘
 */
//@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class DashBoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {
    // 抗锯齿
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectPath = Path()
    private lateinit var pathEffect: PathEffect
    private val oval = RectF()
    private val startAngle = 150f
    private val sweepAngle = 240f
    lateinit var arcRectF: RectF

    init {
        paint.strokeWidth = 2.dp
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
        oval.set(0f, 0f, 10f, 10f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        arcRectF = RectF()
        arcRectF.set(
            width / 2 - 150.dp,
            height / 2 - 150.dp,
            width / 2 + 150.dp,
            height / 2 + 150.dp,
        )
        rectPath.addRect(0f,0f,2.dp,10.dp,Path.Direction.CW)
        pathEffect = PathDashPathEffect(rectPath, 20.dp, 0.dp, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        // 圆弧
        paint.pathEffect = pathEffect
        canvas.drawArc(arcRectF, startAngle, sweepAngle, false, paint)
        paint.pathEffect = null
        canvas.drawArc(arcRectF, startAngle, sweepAngle, false, paint)
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (cos(Math.toRadians(200.0)) * 100.dp).toFloat() + width / 2,
            (100.dp * sin(Math.toRadians(200.0))).toFloat() + height / 2,
            paint
        )
    }
}
