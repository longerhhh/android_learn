package com.example.myapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.myapplication.TAG
import com.example.myapplication.dp
import com.example.myapplication.getAvatar

/**
 * 图文混排
 */
class NewsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    lateinit var staticLayout: StaticLayout
    val textPaint = TextPaint()

    init {
        textPaint.textSize = 20.dp
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        staticLayout =
            StaticLayout.Builder.obtain(TEXT, 0, TEXT.length, textPaint, width - 20.dp.toInt())
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .build()
    }

    private val AVATAR_WIDTH = 100.dp

    private val AVATAR_PADDING = 10.dp

    private val MARGE_TOP = 99.dp

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        // 简单换行的文字绘制
//        canvas.withTranslation(x = 10.dp) {
//            staticLayout.draw(canvas)
//        }

        canvas.drawBitmap(
            getAvatar(AVATAR_WIDTH), width - AVATAR_WIDTH - AVATAR_PADDING,
            MARGE_TOP, paint
        )
        var index = 0
        var i = 1
        var currY = 0f
        do {
            currY += textPaint.fontSpacing
            val maxWidth =
                if (currY > MARGE_TOP
                    && currY /*- textPaint.fontSpacing*/ < AVATAR_PADDING*2 + AVATAR_WIDTH + MARGE_TOP
                ) width - AVATAR_WIDTH - AVATAR_PADDING*2
                else width - AVATAR_PADDING*2
            // measuredWidth：返回的是测试所得的文字宽度
            val count = textPaint.breakText(TEXT, index, TEXT.length, true, maxWidth, null)
            canvas.drawText(TEXT, index, index + count, AVATAR_PADDING, currY, textPaint)
            index += count
            i++
        } while (index + count < TEXT.length)

        /** (start | end | (end - start) | (text.length() - end)
         * 1 | 10 | 9 | 10
         * 0b0001 | 0b0110 | 0b0101 | 0b0110
         * 0001
         * 0110
         * 0101
         * 0110
         * 0111
         * 相当于是 start end (end-start) (length-end) 里面任何一个为负，结果为负，越界
         */

    }
}