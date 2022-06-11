package com.example.myapplication.widget1

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * FileName: BaseGirdDragLayout
 * create date: 2022/5/1
 *
 * @author: longer
 * @description: base drag listener test view goup
 */
open class BaseGirdDragLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    val TAG = this.javaClass.simpleName

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val wm = MeasureSpec.getMode(widthMeasureSpec)
        val hm = MeasureSpec.getMode(heightMeasureSpec)
        measureChildren(
            MeasureSpec.makeMeasureSpec(w / 2, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(h / 3, MeasureSpec.EXACTLY)
        )
        setMeasuredDimension(w, h)
    }

    /**
     * 三行两列
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val halfW = width / 2
        val thirdH = height / 3
        for (i in 0 until childCount) {
            val modI = i % 2
            val halfI = i / 2
            getChildAt(i).layout(
                halfW * modI,
                thirdH * halfI,
                halfW * (modI + 1),
                thirdH * (halfI + 1)
            )
        }
    }
}