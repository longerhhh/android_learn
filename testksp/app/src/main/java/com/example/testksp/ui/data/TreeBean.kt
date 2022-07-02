package com.example.testksp.ui.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TreeBean(
    val title: String,
    val details: List<Pair<String, Int>>,
    val isShow: MutableState<Boolean> = mutableStateOf(false),
)
