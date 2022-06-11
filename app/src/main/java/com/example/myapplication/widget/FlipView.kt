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
 * 折叠的图片
 */
class FlipView @JvmOverloads constructor(
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

    init {
        // 适配手机的z轴位置
        camera.setLocation(0f, 0f, -6 * Resources.getSystem().displayMetrics.density)
//        camera.setLocation(0f,0f,-4*resources.displayMetrics.density)
//        camera.rotateX(30f)
    }

    private val bitmap = getAvatar(imgW)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

//        canvas.save()
//        canvas.translate(cx, cy)
//        camera.applyToCanvas(canvas)
//        canvas.translate(-cx, -cy)
//        canvas.drawBitmap(bitmap, imgL, imgT, paint)
//        canvas.restore()

        // 上半部分
//        canvas.withClip(imgL,imgT,imgR,(imgB-imgT)/2+imgT) {
//            canvas.drawBitmap(bitmap, imgL,imgT,paint)
//        }
        canvas.withTranslation(cx, cy) {
            rotate(-30f)
            // 注意裁剪的范围，最少应是图像宽度的根号2倍，因为图像旋转后上下左右会多出角。
            clipRect(-imgW, -imgW, imgW, 0f)
//            camera.applyToCanvas(this)
            rotate(30f)
            this@withTranslation.translate(-cx, -cy)
            canvas.drawBitmap(bitmap, imgL, imgT, paint)
        }

        // 下半部分
//        canvas.save()
//        canvas.clipRect(imgL, imgT + (imgB - imgT) / 2, imgR, imgB)
        canvas.withTranslation(cx, cy) {
            rotate(-30f)
//            camera.withRotate()
            camera.applyRotateToCanvas(this, 30f)
            // 应当在做三维变换之前就去切割，防止切割图像的范围影响图像的绘制结果
            clipRect(-imgW, 0f, imgW, imgW)
            rotate(30f)
            this@withTranslation.translate(-cx, -cy)
            canvas.drawBitmap(bitmap, imgL, imgT, paint)
        }
//        canvas.restore()

//        canvas.withClip(imgL,imgT+(imgB-imgT)/2,imgR,imgB) {
//        canvas.withTranslation(cx, cy) {
//            camera.applyToCanvas(this)
//            this@withTranslation.translate(-cx, -cy)
//            canvas.drawBitmap(bitmap, imgL, imgT, paint)
//        }
//        }
    }
}