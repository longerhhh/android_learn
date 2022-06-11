package com.example.myapplication.ext

import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import java.util.*

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
 * FileName: ActivityExt
 * Author: longer
 * Date: 2022/4/26
 * Description: Activity的扩展方法
 */
@Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
@kotlin.internal.InlineOnly
inline fun Calendar.setMinute(min: Int) = set(
    Calendar.MINUTE, min
)

fun main() {
    Calendar.getInstance().apply {
        setMinute(10)
        print(this.toString())
    }
}