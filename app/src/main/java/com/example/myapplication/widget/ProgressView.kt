package com.example.myapplication.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.myapplication.dp

class ProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val arcRectF = RectF()
    val textBounds = Rect()
    val TAG = this.javaClass.simpleName

    init {
        paint.strokeWidth = 10.dp
        // 文字水平居中
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 20.dp
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        arcRectF.set(
            width / 2 - 100.dp,
            height / 2 - 100.dp,
            width / 2 + 100.dp,
            height / 2 + 100.dp
        )
    }

    private val text = "bcfBCF完成"

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        paint.style = Paint.Style.STROKE
        // 圆环背景
        paint.setColor(Color.LTGRAY)
        canvas.drawCircle(width / 2f, height / 2f, 100.dp, paint)
        // 圆头线
        paint.strokeCap = Paint.Cap.ROUND
        // 圆环进度
        paint.setColor(Color.GREEN)
        canvas.drawArc(arcRectF, 270f, 270f, false, paint)

        paint.setColor(Color.BLACK)
        // 用STROKE绘制的文字是描边的，用FILL才是实心的
        paint.style = Paint.Style.FILL
        // 文字垂直位置调整
        // textBounds 文字的开始为left=0，左负右正，文字的baseLine为bottom=0，上负下正，
        // 所以居中位置在baseline之上，为负，故减去可居中
        paint.getTextBounds(text, 0, text.lastIndex, textBounds)
        Log.d(
            TAG, "onDraw: text bounds: " +
                    "${textBounds.left}, ${textBounds.top}, ${textBounds.right}, ${textBounds.bottom}"
        )
        // 用文字四围实现垂直居中，默认的x和y位置所在为baseLine所在，适用于固定文字情形
        val midY = height / 2 - (textBounds.top + textBounds.bottom) / 2f
//        canvas.drawText(text, width / 2f, midY, paint)
        // 使用文字的上下baseLine实现文字居中，通用，不会因文字的变化而变化
        val fontMetrics = paint.fontMetrics
        val y = height/2-(fontMetrics.ascent + fontMetrics.descent)/2
        canvas.drawText(text,width/2f,y,paint)
    }
}