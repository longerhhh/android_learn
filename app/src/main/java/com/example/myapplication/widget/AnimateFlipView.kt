package com.example.myapplication.widget

import android.content.Context
import android.content.res.Resources
import android.graphics.Camera
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.example.myapplication.dp
import com.example.myapplication.getAvatar
import com.example.myapplication.applyRotateToCanvas

/**
 * 动画的的图片
 */
//@Keep
class AnimateFlipView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    val imgW = 300.dp
    val imgL
        get() = width / 2 - 150.dp
    val imgT
        get() = height / 2 - 150.dp
    val imgR
        get() = width / 2 + 150.dp
    val imgB
        get() = height / 2 + 150.dp
    val cx
        get() = width / 2f
    val cy
        get() = height / 2f
    val camera = Camera()

    // 图像下半部分裁切角度
    var flipDegree = 0f
        set(value) {
            field = value
            invalidate()
        }

    // 图像下半部分三维旋转角度
    var topRotateDegree = 0f
        set(value) {
            field = value
            invalidate()
        }

    // 图像下半部分三维旋转角度
    var rotateDegree = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        // 适配手机的z轴位置
        camera.setLocation(0f, 0f, -6 * Resources.getSystem().displayMetrics.density)
//        camera.setLocation(0f,0f,-4*resources.displayMetrics.density)
//        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    private val bitmap = getAvatar(imgW)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        // 上半部分
        canvas.withTranslation(cx, cy) {
            rotate(-flipDegree)
            clipRect(-imgW, -imgW, imgW, 0f)
            camera.applyRotateToCanvas(canvas, x = topRotateDegree)
            // 注意裁剪的范围，最少应是图像宽度的根号2倍，因为图像旋转后上下左右会多出角。
            rotate(flipDegree)
            this@withTranslation.translate(-cx, -cy)
            canvas.drawBitmap(bitmap, imgL, imgT, paint)
        }

        // 下半部分
        canvas.withTranslation(cx, cy) {
            rotate(-flipDegree)
            camera.applyRotateToCanvas(canvas, x = rotateDegree)
            // 应当在做三维变换之前就去切割，防止切割图像的范围影响图像的绘制结果
            clipRect(-imgW, 0f, imgW, imgW)
            rotate(flipDegree)
            this@withTranslation.translate(-cx, -cy)
            canvas.drawBitmap(bitmap, imgL, imgT, paint)
        }
    }
}
