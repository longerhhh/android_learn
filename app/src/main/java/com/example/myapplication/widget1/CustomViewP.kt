package com.example.myapplication.widget1

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.OverScroller
import com.drake.tooltip.toast
import kotlin.math.abs

/**
 * 自定义的仿viewpager效果，左右滑动切换子view，边界控制，
 * 后续如果再加入定时切换view及首尾view的切换就可实现轮播广告栏效果
 */
class CustomViewP @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    val TAG = this.javaClass.simpleName

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (!changed) return

        var left = l
        var right = r
        for (i in 0 until childCount) {
            getChildAt(i).layout(left, t, right, b)
            left += width
            right += width
        }
    }

    private val velocityTracker: VelocityTracker = VelocityTracker.obtain()
    private val overScroller = OverScroller(context, DecelerateInterpolator())
    private val viewConfiguration = ViewConfiguration.get(context)

    var downX = 0f
    var downY = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        velocityTracker.addMovement(ev)
        if (ev == null) return false

        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                offset = downX
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(downX - ev.x) > viewConfiguration.scaledPagingTouchSlop) {
                    return true
                }
            }
        }
        return false //super.onInterceptTouchEvent(ev)
    }

    var offset = 0f

    /**
     * x:
     *      downX   -> x
     * offset:
     *      0       -> downX - x
     */
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (ev == null) return false

        val scrollOffset = downX - ev.x
        velocityTracker.addMovement(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                scrollTo((scrollOffset).toInt() + width * currPage, 0)
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000)
                val xVelocity = velocityTracker.xVelocity
                if (abs(xVelocity) > viewConfiguration.scaledMinimumFlingVelocity) {
                    toast("fling")
                    if (xVelocity > 0) {
                        showPreviousPage(scrollOffset)
                    } else {
                        showNextPage(scrollOffset)
                    }
                } else {
                    when {
                        scrollOffset > width / 2 -> {
                            showNextPage(scrollOffset)
                        }
                        scrollOffset < -width / 2 -> {
                            showPreviousPage(scrollOffset)
                        }
                        else -> {
                            resetToCurrPage(scrollOffset)
                        }
                    }
                }
                postInvalidateOnAnimation()
            }
        }
        return true // super.onTouchEvent(ev)
    }

    var currPage = 0

    /***********************************************************
     * overScroll.startScroll(startX, startY, distanceX, distanceY)
     * 注意后两个参数是distance，而不是destination
     */

    fun showPreviousPage(scrollOffset: Float) {
        if (currPage == 0) {
            resetToCurrPage(scrollOffset)
        } else {
            overScroller.startScroll(
                scrollOffset.toInt() + (currPage) * width, 0,
                -width - scrollOffset.toInt(), 0
            )
            currPage -= 1
        }
    }

    fun showNextPage(scrollOffset: Float) {
        if (currPage == childCount - 1) {
            resetToCurrPage(scrollOffset)
        } else {
            toast("next")
            overScroller.startScroll(
                scrollOffset.toInt() + (width * currPage), 0,
                (width - scrollOffset).toInt(), 0
            )
            currPage += 1
        }
    }

    fun resetToCurrPage(scrollOffset: Float) {
        overScroller.startScroll(
            scrollOffset.toInt() + (width * currPage), 0,
            (-scrollOffset).toInt(), 0
        )
    }

    override fun computeScroll() {
        super.computeScroll()
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}