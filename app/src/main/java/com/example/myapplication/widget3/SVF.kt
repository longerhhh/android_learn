package com.example.myapplication.widget3

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.forEachIndexed
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.drake.tooltip.toast
import kotlin.math.abs

private const val DEFAULT_SCROLL_DISTANCE = 15000

/**
 * FileName: SVF
 * create date:
 *
 * @author: longer
 * @description:
 */
class SVF(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs),Runnable {
    
    private val TAG: String = this.javaClass.simpleName
    
    private val viewConfiguration: ViewConfiguration = ViewConfiguration.get(context)
    
    var downX = 0f
    var downTime = 0L
    private var isScrolling = false
    
    var displayedChild = 0
    
    val previousChild: Int
        get() {
            val pre = displayedChild - 1
            return if (pre < 0) pre + childCount else pre
        }
    
    val nextChild: Int
        get() {
            val next = displayedChild + 1
            return if (next >= childCount) next - childCount else next
        }
    
    val previousView get() = getChildAt(previousChild)
    val nextView get() = getChildAt(nextChild)
    val currentView get() = getChildAt(displayedChild)
    
    private val currViewAnimator by lazy { getObjectAnimatorInstance().apply { duration=1000 } }
    
    private val flipViewAnimator by lazy {
        getObjectAnimatorInstance().apply {
            duration = 1000
            doOnEnd {
                if (isFlip) {
                    displayedChild = if (showNext) nextChild else previousChild
                    changeChildrenVisibility()
                }
                onFlipEndListener?.invoke(displayedChild)
            }
        }
    }
    
    val defaultInAnimation: Animation? =
        AnimationUtils.makeInAnimation(context, false).apply { addEndCallback() }
    val defaultOutAnimation: Animation? = AnimationUtils.makeOutAnimation(context, false)
    
    var inAnimation: Animation? = defaultInAnimation
        set(value) {
            field = value
            value?.addEndCallback()
        }
    
    private var isFlip = false
    private var showNext = false
    
    var onFlipEndListener: ((Int) -> Unit)? = null
    
    var outAnimation: Animation? = defaultOutAnimation
    
    var isFlipping = false
        private set(value) {
            field = value
            if (value && !isFlippingEnable) {
                isFlippingEnable = value
            }
        }
    
    var isFlippingEnable = false
    var flipInterval = 2000L
    
    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener(){
        override fun onDown(e: MotionEvent?): Boolean {
            if (e == null || childCount < 2) {
                return false
            }
            if (previousView.scrollX == DEFAULT_SCROLL_DISTANCE && childCount >= 3) {
                previousView.scrollX = width
            }
            if (nextView.scrollX == -DEFAULT_SCROLL_DISTANCE) {
                nextView.scrollX = -width
            }
            if (currViewAnimator.isRunning || flipViewAnimator.isRunning) {
                currViewAnimator.pause()
                flipViewAnimator.pause()
                isScrolling = true
            }
            return true
        }
    
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (e1 == null || e2 == null) return false
            Log.d(TAG, "onScroll: distanceX = $distanceX")
            if (abs(currentView.scrollX)>=width) {
                currViewAnimator.cancel()
                flipViewAnimator.cancel()
            }
            currentView.scrollBy(distanceX.toInt(),0)
            if (childCount == 0 && !isScrolling) {
                if (currentView.scrollX > 0) {
                    nextView.scrollX = -width
                } else {
                    previousView.scrollX = width
                }
            }
            nextView.scrollBy(distanceX.toInt(), 0)
            if (!isScrolling) {
                isScrolling = true
            }
            if (childCount >= 3) {
                previousView.scrollBy(distanceX.toInt(), 0)
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 == null || e2 == null) return false
            isScrolling = false
//            updateChildrenNew(e2.x-downX, velocityX)
            updateChildrenNew(-currentView.scrollX.toFloat(), velocityX)
            Log.d(TAG, "onFling: update children")
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            if (e == null) return false
//            if (isScrolling) {
//                isScrolling = false
//                Log.d(TAG, "onSingleTapUp: update children")
//                updateChildrenNew(e.x-downX)
//            }
            return super.onSingleTapUp(e)
        }
    
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            if (!isScrolling) {
                performClick()
            }
            return super.onSingleTapConfirmed(e)
        }
    })
    
    init {
        gestureDetector.setIsLongpressEnabled(false)
    }
    
    private fun getObjectAnimatorInstance() = ObjectAnimator().apply {
        setPropertyName("scrollX")
        interpolator = DecelerateInterpolator()
    }
    
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev == null) return super.onInterceptTouchEvent(ev)
        
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downTime = System.currentTimeMillis()
                parent.requestDisallowInterceptTouchEvent(true)
                isScrolling = true
                if (isFlipping) {
                    stopFlipping()
                }
                return true
            }
        }
        return false
    }
    
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (ev == null || childCount < 2) return super.onTouchEvent(ev)
        
        val onTouchEventResult = gestureDetector.onTouchEvent(ev)
    
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (isFlipping) {
                    stopFlipping()
                }
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (isFlippingEnable) {
                    startFlipping()
                }
                if (isScrolling) {
                    isScrolling = false
                    Log.d(TAG, "onTouchEvent: update children")
                    updateChildrenNew(-currentView.scrollX.toFloat())
//                    updateChildrenNew(ev.x-downX)
                }
            }
        }
    
        return onTouchEventResult
    }
    
    private fun updateChildrenNew(distance: Float, xVel: Float = 0f) {
        val isShowNext: Boolean
        val isShowPre: Boolean
        if (xVel == 0f) {
            isShowNext = distance < -width / 2
            isShowPre = distance > width / 2
        } else {
            isShowNext = distance < 0 && xVel < -viewConfiguration.scaledMinimumFlingVelocity
            isShowPre = distance > 0 && xVel > viewConfiguration.scaledMinimumFlingVelocity
        }
        
        when {
            isShowNext -> {
                flipView(IS_NEXT or IS_FLIP)
            }
            isShowPre -> {
                flipView(IS_FLIP)
            }
            else -> {
                flipView(if (distance < 0) IS_NEXT else 0)
            }
        }
    }
    
    private fun flipView(state: Int) {
        Log.d(TAG, "flipView: state = $state")
        showNext = state and IS_NEXT == IS_NEXT
        isFlip = state and IS_FLIP == IS_FLIP
        currViewAnimator.target = currentView
        flipViewAnimator.target = if (showNext) nextView else previousView
        
        if (isFlip) {
            currViewAnimator.setIntValues(if (showNext) width else -width)
            flipViewAnimator.setIntValues(0)
        } else {
            currViewAnimator.setIntValues(0)
            flipViewAnimator.setIntValues(if (showNext) -width else width)
        }
        
        currViewAnimator.start()
        flipViewAnimator.start()
    }
    
    private fun changeChildrenVisibility() {
        when (childCount) {
            1 -> {
                changeVisibilityToDefault(currentView)
            }
            in Int.MIN_VALUE..0 -> {
                return
            }
            else -> {
                changeChildren3Visibility()
            }
        }
    }
    
    private fun changeChildren3Visibility() {
        val preI = previousChild
        val currI = displayedChild
        val nextI = nextChild
        val width = if (width == 0) DEFAULT_SCROLL_DISTANCE else width
        forEachIndexed { index, view ->
            view.run {
                when (index) {
                    preI -> {
                        if (isGone) isGone = false
                        scrollTo(width, 0)
                    }
                    currI -> {
                        if (isGone) isGone = false
                        scrollTo(0, 0)
                    }
                    nextI -> {
                        if (isGone) isGone = false
                        scrollTo(-width, 0)
                    }
                    else -> {
                        if (isVisible) isVisible = false
                    }
                }
            }
        }
    }
    
    private fun changeVisibilityToDefault(vararg views: View) {
        if (views.isEmpty()) return
        for (v in views) {
            if (!v.isVisible) v.isVisible = true
            v.scrollTo(0, 0)
        }
    }
    
    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)
        if (isAttachedToWindow) {
            changeChildrenVisibility()
        }
    }
    
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        changeChildrenVisibility()
        if (isFlippingEnable && childCount > 1) startFlipping()
    }
    
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopFlipping()
    }
    
    override fun onFinishInflate() {
        super.onFinishInflate()
        changeChildrenVisibility()
    }
    
    private fun Animation.addEndCallback() {
        doOnEnd {
            changeChildrenVisibility()
            onFlipEndListener?.invoke(displayedChild)
        }
    }
    
    fun startFlipping() {
        if (childCount == 0 || isFlipping) {
            return
        }
        if (displayedChild > childCount) {
            displayedChild = 0
        }
        postDelayed(this, flipInterval)
        isFlipping = true
    }
    
    fun stopFlipping() {
        removeCallbacks(this)
        isFlipping = false
    }
    
    fun flipView() {
        kotlin.runCatching { // 在删除子view后滚动可能会出现异常，所以把异常包起来
            val nextV = nextView
            if (nextV.scrollX != 0) {
                nextV.scrollX = 0
            }
            val nextChild = nextChild
            val displayedChild = displayedChild
            forEachIndexed { index, view ->
                if (index == displayedChild) {
                    view.startAnimation(outAnimation)
                } else if (index == nextChild) {
                    view.startAnimation(inAnimation)
                }
            }
            this.displayedChild = nextChild
        }
    }
    
    companion object {
        const val IS_NEXT = 1
        const val IS_FLIP = 2
        
        inline fun Animation.doOnEnd(crossinline block: (Animation) -> Unit) {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    animation?.also(block) //block(animation)
                }
                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
    }
    
    override fun run() {
        if (childCount < 2) return
        flipView()
        postDelayed(this, flipInterval)
    }
    
    override fun performClick(): Boolean {
        toast("click")
        return super.performClick()
    }
}