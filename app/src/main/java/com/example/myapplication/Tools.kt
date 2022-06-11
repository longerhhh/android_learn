package com.example.myapplication

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

object Tools {

    // 解析结果为空
    fun getAvatar(width: Float) :Bitmap{
        val options = BitmapFactory.Options()
        // 只解析图像外围属性，不解析图像，返回空，执行结束options的out...属性会被赋值
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_avator, options)
        options.inJustDecodeBounds = false
        // 上面解析到的outWith
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_avator, options)
    }
}