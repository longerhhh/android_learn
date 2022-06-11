package com.example.myapplication.widget

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.annotation.FloatRange
import androidx.core.animation.addListener
import androidx.core.view.GestureDetectorCompat
import com.example.myapplication.dp
import com.example.myapplication.getAvatar
import kotlin.math.abs

/**
 * 显示图片并实现双击放大以及双指缩放和放大后的单指滑动，没有调优，有些边界控制没有处理好，但基本的功能有了，后面有空想调的话再调吧
 */
class ScrollImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener, ScaleGestureDetector.OnScaleGestureListener, Runnable {

    val TAG = this.javaClass.simpleName

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bmpW = 200.dp
    val bmp: Bitmap = getAvatar(bmpW)
    val radius = bmpW / 2f
    val h2 = bmp.height / 2f
    val w2 = bmp.width / 2f
    val detectorCompat = GestureDetectorCompat(context, this)
    val scaleAnim = ObjectAnimator.ofFloat(this, "fraction", 0f, 1f)
    val doubleScaleAnim = ObjectAnimator.ofFloat(this, "fraction", 1f, 2f)
    val resetScaleAnim = ObjectAnimator.ofFloat(this, "fraction", 2f, 0f)
    val overScroller = OverScroller(context)
    val scaleGestureDetector =  ScaleGestureDetector(context, this)

    @FloatRange(from = 0.0)
    var cx = 0f

    @FloatRange(from = 0.0)
    var cy = 0f

    @FloatRange(from = 0.0)
    var smallScale = 0f

    @FloatRange(from = 0.0)
    var bigScale = 0f

    var offsetX = 0f
    var offsetY = 0f

    @FloatRange(from = 0.0)
    var maxOffsetX = 0f

    @FloatRange(from = 0.0)
    var maxOffsetY = 0f

    // 测试大小缩放是否正常时使用
    private var isBigScale = false
        set(value) {
            field = value
            invalidate()
        }

    // 缩放比率
    @FloatRange(from = 0.0)
    var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        val onEndListener: (animator: Animator) -> Unit = {
            maxOffsetX = (bmp.width * scaleSize - width) / 2f
            maxOffsetY = (bmp.height * scaleSize - height) / 2f
        }
        scaleAnim.addListener(onEnd = onEndListener)
        doubleScaleAnim.addListener(onEnd = onEndListener)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        cx = width / 2f
        cy = height / 2f
        val widthScale = width / bmp.width.toFloat()
        val heightScale = height / bmp.height.toFloat()
        smallScale = minOf(widthScale, heightScale)
        bigScale = maxOf(widthScale, heightScale)

        Log.d(
            TAG,
            "onSizeChanged: cy = $cy, height = $height, radius = $radius, bmpHeight = ${bmp.height}, bmpW = $bmpW"
        )
    }

    var currX = 0f

    val scaleSize
        get() = (bigScale - smallScale) * fraction + smallScale
    val distanceX
        get() = -(scaleSize-smallScale)*(currX-cx)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

//        checkScrollable {
            canvas.translate(distanceX, offsetY*fraction)
//        }
//        val scaleSize = if (isBigScale) bigScale else smallScale
        // 指定轴心的缩放
        canvas.scale(scaleSize, scaleSize, cx, cy)
        canvas.drawBitmap(bmp, cx - w2, cy - h2, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var r = scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            r = detectorCompat.onTouchEvent(event)
        }
        return r
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onScroll(
        down: MotionEvent?,
        currEvent: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        checkScrollable {
            Log.d(
                TAG,
                "onScroll: ox = $offsetX, oy = $offsetY, mox = $maxOffsetX, moy = $maxOffsetY"
            )
            var shouldInvalidate = false
            if (abs(offsetX) <= maxOffsetX) {
                // 指定刷新条件，边界不刷新，减少无用刷新，提高性能
                shouldInvalidate =
                    if (offsetX == maxOffsetX) distanceX < 0 else if (offsetX == -maxOffsetX) distanceX > 0 else true
                offsetX -= distanceX
                if (abs(offsetX) > maxOffsetX) {
                    offsetX = if (offsetX < 0) -maxOffsetX else maxOffsetX
                }
            }
            if (abs(offsetY) <= maxOffsetY) {
                shouldInvalidate =
                    if (offsetY == maxOffsetY) distanceY < 0 else if (offsetY == -maxOffsetY) distanceY > 0 else true
                offsetY -= distanceY
                if (abs(offsetY) > maxOffsetY) {
                    offsetY = if (offsetY < 0) -maxOffsetY else maxOffsetY
                }
//                shouldInvalidate = true
            }
            if (shouldInvalidate) invalidate()
        }
        return false
    }

    fun shouldInvalidate(offset: Float, maxOffset: Float, distance: Float): Boolean {
        return if (offset == maxOffset) distance < 0 else if (offset == -maxOffset) distance > 0 else true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    // 惯性滚动
    override fun onFling(
        down: MotionEvent?,
        event: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        overScroller.fling(
            offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
            (-maxOffsetX).toInt(), maxOffsetX.toInt(), (-maxOffsetY).toInt(), maxOffsetY.toInt()
        )
        postOnAnimation(this)
        return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }

    var doubleTapCount = 0

    override fun onDoubleTap(e: MotionEvent?): Boolean {
//        isBigScale = !isBigScale
        if (e == null) return false

        currX = e.x

        when (doubleTapCount) {
            0 -> {
                doubleTapCount += 1
                scaleAnim.start()
                //            if (fraction == 0f) scaleAnim.start() else scaleAnim.reverse()
            }
            1 -> {
                doubleTapCount += 1
                doubleScaleAnim.start()
            }
            else -> {
                doubleTapCount = 0
                resetScaleAnim.start()
                maxOffsetX = 0f
                maxOffsetY = 0f
            }
        }
        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    inline fun checkScrollable(block: () -> Unit) {
        if (fraction >= 1) block()
    }

    override fun run() {
        if (overScroller.computeScrollOffset()) {
            offsetX = overScroller.currX.toFloat()
            offsetY = overScroller.currY.toFloat()
            invalidate()
            postOnAnimation(this)
        }
    }

    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        if (detector == null) return false

        fraction = detector.scaleFactor
        return false
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {
    }
}