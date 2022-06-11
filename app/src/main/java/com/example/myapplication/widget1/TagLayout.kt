package com.example.myapplication.widget1

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import androidx.core.util.size
import com.example.myapplication.TAG

/**
 * FileName: TagLayout
 * create date: 2022/4/28 21:50
 *
 * @author: longer
 * @description: viewgroup 处理
 */
class TagLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val childrenPositions = SparseArray<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var currHeight = 0
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
            val measuredHeight = child.measuredHeight
            val measuredWidth = child.measuredWidth
            if (i >= childrenPositions.size) {
                childrenPositions.append(i, Rect())
            }
            if (widthUsed + measuredWidth > w) {
                widthUsed = measuredWidth
                heightUsed += currHeight
                currHeight = 0
            } else {
                widthUsed += measuredWidth
            }
            childrenPositions[i].set(widthUsed-measuredWidth, heightUsed, widthUsed,heightUsed+measuredHeight)
            val r = childrenPositions[i]
            Log.d(TAG, "onMeasure$i: l: ${r.left}, t:${r.top}, r:${r.right}, b:${r.bottom}")
            currHeight = maxOf(currHeight, measuredHeight)
        }
        heightUsed += currHeight
        val mh = MeasureSpec.makeMeasureSpec(heightUsed, MeasureSpec.EXACTLY)
        setMeasuredDimension(widthMeasureSpec, mh)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childPosition = childrenPositions.valueAt(i)
            child.layout(childPosition.left, childPosition.top,childPosition.right,childPosition.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}