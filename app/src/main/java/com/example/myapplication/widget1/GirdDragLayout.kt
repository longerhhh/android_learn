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
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import androidx.core.view.isInvisible
import androidx.core.view.isVisible


/**
 * FileName: GirdDragLayout
 * Author: longer
 * Date: 2022/4/28
 * Description: test drag listener of view group
 */
class GirdDragLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseGirdDragLayout(context, attrs, defStyleAttr) {

    var draggingView: View = this


    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)
        for (i in 0 until childCount) {
            getChildAt(i).apply {
                setOnLongClickListener { v ->
                    draggingView = v
                    v.startDragAndDrop(null, DragShadowBuilder(v), v, 0)
                }
                /**
                 * 拖动事件会回调当前view group下除正在拖动的view之外所有可见view的drag event
                 * start事件，所有非拖动的view 都能收到一次
                 * entered事件，拖动view的中心到达指定view，指定view能收到一次
                 * location事件，所在view收到多次，一直拖着不放，一直收到
                 * exited事件，拖动view中心离开当前所在view，当前所在view收到一次
                 * ended事件，所有非拖动的view 都能收到一次
                 * drop事件，拖动view中心所在view收到一次，如果所在位置为拖动view之前的位置，没有任何view收到
                 */
                setOnDragListener { v, event ->
                    when (event.action) {
                        DragEvent.ACTION_DRAG_STARTED -> {
                            Log.d(TAG, "onViewAdded$i: drag started")
                        }
                        DragEvent.ACTION_DRAG_ENTERED -> {
                            Log.d(TAG, "onViewAdded$i: drag entered")
                        }
                        DragEvent.ACTION_DRAG_LOCATION -> {
                            Log.d(TAG, "onViewAdded$i: drag location")
                            // sort
                        }
                        DragEvent.ACTION_DRAG_EXITED -> {
                            Log.d(TAG, "onViewAdded$i: drag exited")
                        }
                        DragEvent.ACTION_DRAG_ENDED -> {
                            Log.d(TAG, "onViewAdded$i: drag ended")
                        }
                        DragEvent.ACTION_DROP -> {
                            Log.d(TAG, "onViewAdded$i: drop")
                        }
                    }
                    true
                }
            }
        }
    }
}