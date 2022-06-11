package com.example.myapplication

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.util.TypedValue
import android.view.View

internal val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

internal fun View.getAvatar(width: Int): Bitmap {
    val options = BitmapFactory.Options()
    // 只解析图像外围属性，不解析图像，返回空，执行结束options的out...属性会被赋值
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.drawable.ic_avator, options)
    options.inJustDecodeBounds = false
    // 上面解析到的outWith
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(resources, R.drawable.ic_avator, options)
}

internal fun View.getAvatar(width: Float) = getAvatar(width.toInt())

internal val View.TAG
    get() = this.javaClass.simpleName

internal fun Camera.applyRotateToCanvas(
    canvas: Canvas,
    x: Float = 0f,
    y: Float = 0f,
    z: Float = 0f,
) {
    save()
    rotate(x, y, z)
    applyToCanvas(canvas)
    restore()
}