package com.example.myapplication.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.compose.ui.graphics.Color
import androidx.core.animation.addListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.myapplication.R
import com.example.myapplication.TAG
import com.example.myapplication.dp

/**
 * edit text over load 会出错，下面的横线无法显示，也无法编辑，所以只能去调用xml中使用的构造方法
 */
class MEditText constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var isMaterialEnable = true
    // 上方的提示文字是否显示出来了，使用它防止输入多个文字时重复出现动画
    var isHintShow = false

    var offset = 20.dp
    var hintPaddingTop = 0
        set(value) {
            field = value
            invalidate()
        }

    var hintAlpha = 0
        set(value) {
            field = value
            invalidate()
        }

    val bgPadding = Rect()

    init {
//        val typedValue = context.obtainStyledAttributes(attrs, R.styleable.MEditText)
//        isMaterialEnable = typedValue.getBoolean(R.styleable.MEditText_isMaterialAble, true)
//        typedValue.recycle()
        background?.getPadding(bgPadding)
        if (background == null) {
            Log.d(TAG, "init: background = null")
            bgPadding.set(5, 5, width - 5, height - 5)
        }
        if (isMaterialEnable/* && isHintShow*/) {
            setPadding(
                bgPadding.left,
                bgPadding.top + offset.toInt(),
                bgPadding.right,
                bgPadding.bottom
            )
        }
        paint.textSize = 12.dp
        paint.color = android.graphics.Color.DKGRAY
        hintPaddingTop = paint.fontSpacing.toInt()
//        // 从不显示到显示
        val textChangeAnim =
            ObjectAnimator.ofInt(this, "hintPaddingTop", paint.fontSpacing.toInt(), 0)
        val textChangeAnimAlpha = ObjectAnimator.ofInt(this, "hintAlpha", 0, 0xff)
        val animSet = AnimatorSet().apply {
            duration = 1000
            playTogether(textChangeAnim, textChangeAnimAlpha)
//            start()
        }
        doAfterTextChanged { text ->
            // 有字，提示没显示，让他显示出来
            if (!text.isNullOrEmpty() && !isHintShow) {
                animSet.start()
                isHintShow = true
//                没字，提示显示了，然他消失掉
            } else if (text.isNullOrEmpty() && isHintShow) {
//                textChangeAnim.start()
                animSet.reverse()
                isHintShow = false
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        if (isMaterialEnable /*&& isHintShow*/) {
            paint.alpha = hintAlpha
            canvas.drawText(
                hint?.toString() ?: "",
                0f + 2.dp,
                paint.fontSpacing + hintPaddingTop,
                paint
            )
        }
    }
}