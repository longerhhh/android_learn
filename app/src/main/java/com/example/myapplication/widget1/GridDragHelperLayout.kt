/*
 * Copyright (C), 2022 LJTY's Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.myapplication.widget1

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.GridLayoutAnimationController
import androidx.activity.result.ActivityResultLauncher
import androidx.core.text.isDigitsOnly
import androidx.customview.widget.ViewDragHelper
import com.drake.engine.utils.isNumber


/**
 * FileName: GridDragHelperLayout
 * Author: longer
 * Date: 2022/4/28
 * Description: test drag helper of view group
 */
class GridDragHelperLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseGirdDragLayout(context, attrs, defStyleAttr) {

    val dragHelper = ViewDragHelper.create(this, DragHelperCallback())
    val gridLayoutAnimationController = GridLayoutAnimationController(context, attrs)

    init {
        gridLayoutAnimationController.setAnimation(AnimationUtils.makeInAnimation(context,true))
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return ev?.let { dragHelper.shouldInterceptTouchEvent(it) }
            ?: super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (event != null) {
            dragHelper.processTouchEvent(event)
            true
        } else super.onTouchEvent(event)
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper.continueSettling(true)) {
            postInvalidateOnAnimation()
        }
    }

    inner class DragHelperCallback : ViewDragHelper.Callback() {
        // true 抓住，false不抓住
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        // 水平方向移动，返回0不可移动
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        // 垂直方向移动，返回0不可移动
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        var capturedChildLeft = 0
        var capturedChildTop = 0

        // 抓住view后的初始化操作
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            super.onViewCaptured(capturedChild, activePointerId)
            Log.d(TAG, "onViewCaptured: ")
            capturedChildLeft = capturedChild.left
            capturedChildTop = capturedChild.top
        }

        // view移动中的位置变化
        override fun onViewPositionChanged(
            changedView: View, left: Int, top: Int, dx: Int, dy: Int
        ) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
            Log.d(TAG, "onViewPositionChanged: $left,$top,$dx,$dy")
        }

        // 拖动状态
        override fun onViewDragStateChanged(state: Int) {
            super.onViewDragStateChanged(state)
        }

        // 放手后操作
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            Log.d(TAG, "onViewReleased: $xvel,$yvel")
//            gridLayoutAnimationController.start()
            dragHelper.settleCapturedViewAt(capturedChildLeft,capturedChildTop)
            postInvalidateOnAnimation()
        }
    }
}