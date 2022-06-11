package com.example.myapplication.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

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

/**
 * FileName: ImageViewExt
 * Author: longer
 * Date: 2022/4/26
 * Description: imageView 的扩展
 */
inline fun <T : ImageView> T.loadImageByGlide(
    src: Any?,
    block: RequestBuilder<Drawable>.() -> Unit = {}
) = Glide.with(this).load(src).apply(block).into(this)