package com.example.testksp.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LCPager() {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = 3, modifier = Modifier.fillMaxSize(), state = pagerState,
            verticalAlignment = Alignment.Top
        ) {
            when (it) {
                0 -> Home()
                1 -> Mid()
                2 -> Mine()
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 30.dp)
        )
    }
}