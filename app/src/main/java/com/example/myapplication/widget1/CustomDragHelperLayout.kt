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
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.customview.widget.ViewDragHelper


/**
 * FileName: CustomDragHelperLayout
 * Author: longer
 * Date: 2022/4/28
 * Description: try to use drag helper in view group
 */
class CustomDragHelperLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val dragHelper = ViewDragHelper.create(this, ViewDragHelperCallback())
    val viewConfiguration = ViewConfiguration.get(context)

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

    inner class ViewDragHelperCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        var capturedChildTop = 0

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            super.onViewCaptured(capturedChild, activePointerId)
            capturedChildTop = capturedChild.top
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            if (capturedChildTop == 0 && (yvel > viewConfiguration.scaledMinimumFlingVelocity || releasedChild.top > (height - releasedChild.height) / 2)) {
                dragHelper.settleCapturedViewAt(0, height - releasedChild.height)
            } else if (capturedChildTop > 0 && (yvel < -viewConfiguration.scaledMinimumFlingVelocity || releasedChild.top < (height - releasedChild.height) / 2)) {
                dragHelper.settleCapturedViewAt(0, 0)
            } else {
                dragHelper.settleCapturedViewAt(0, capturedChildTop)
            }
            postInvalidateOnAnimation()
        }
    }
}