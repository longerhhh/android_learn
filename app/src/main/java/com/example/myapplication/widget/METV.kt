package com.example.myapplication.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.TAG
import com.example.myapplication.dp

/**
 * 用动画完成比例来实现对应的alpha值和hint位置的实现可能性能会更好
 */
class METV @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatEditText(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var isMaterialEnable = true

    //    var isHintShow = false
    var offset = 20.dp
    var hintPaddingTop = 0

    var animFraction: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    val bgPadding = Rect()

    // 从不显示到显示
    var hintAnim: ObjectAnimator? = null

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
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        if (hintAnim == null) {
            hintAnim = ObjectAnimator.ofFloat(this, "animFraction", 0f, 1f)
        }
        if (hintAnim != null) {
            // 有字，提示没显示，让他显示出来
            if (!text.isNullOrEmpty()/* && !isHintShow*/) {
//                isHintShow = true
            hintAnim?.start()
//                没字，提示显示了，然他消失掉
            } else if (text.isNullOrEmpty()/* && isHintShow*/) {
//                textChangeAnim.start()
            hintAnim?.reverse()
//                isHintShow = false
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        if (isMaterialEnable /*&& isHintShow*/) {
            paint.alpha = (animFraction * 0xff).toInt()
            canvas.drawText(
                hint?.toString() ?: "",
                0f + 2.dp,
                paint.fontSpacing * (1 - animFraction) + hintPaddingTop,
                paint
            )
        }
    }
}