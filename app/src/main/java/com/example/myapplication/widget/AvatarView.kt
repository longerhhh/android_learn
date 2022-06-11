package com.example.myapplication.widget

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.Tools
import com.example.myapplication.dp
import com.example.myapplication.getAvatar

/**
 * 带边框的头像框
 */
class AvatarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // xfermode = transfermode, 蒙版绘制
    // src_in source in destination, 只绘制源图像在目标区域的部分，其余舍弃
    val srcInXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    val bounds = RectF()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bounds.set(width/2-100.dp, height/2-100.dp, width/2+100.dp, height/2+100.dp)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        // 外部边框绘制
        paint.strokeWidth = 10.dp
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(width/2f, height/2f, 100.dp + 5.dp, paint)
        // 离屏缓冲, bounds为缓冲区域，
        val count = canvas.saveLayer(bounds, paint)
        paint.style = Paint.Style.FILL
        canvas.drawCircle(width/2f, height/2f, 100.dp, paint)
        paint.xfermode = srcInXfermode
        canvas.drawBitmap(getAvatar(200.dp), width/2-100.dp, height/2-100.dp, paint)
        // 恢复
        paint.xfermode = null
        canvas.restoreToCount(count)
    }
}