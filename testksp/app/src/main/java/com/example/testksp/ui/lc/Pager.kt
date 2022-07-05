package com.example.testksp.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.testksp.ui.nav.HOME
import com.example.testksp.ui.nav.MID
import com.example.testksp.ui.nav.MINE
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LCPager(modifier: Modifier = Modifier, pagerState: PagerState = rememberPagerState()) {
    Box(modifier = modifier) {
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

@Composable
fun TestTabs(
    sel: MutableState<Int> = remember {
        mutableStateOf(0)
    },
    itemTitles: SnapshotStateList<String>
) {
    TabRow(selectedTabIndex = sel.value,
            Modifier.background(Color.White), backgroundColor = Color.LightGray) {
        itemTitles.forEachIndexed { index, title ->
            Tab(
                selected = sel.value == index,
                onClick = { sel.value = index },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Black
            ) {
                Text(
                    text = title,
                    Modifier
                        .padding(horizontal = 10.dp)
                        .background(Color.Green, RoundedCornerShape(20.dp)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabPager() {
    val pagerState = rememberPagerState()
    val selIndex = remember {
        mutableStateOf(0)
    }
    if (pagerState.currentPage != selIndex.value) {
        LaunchedEffect(key1 = "page", block = {
            pagerState.animateScrollToPage(selIndex.value, pageOffset = 1f)
        })
    }
    val itemTitles = remember {
        mutableStateListOf(
            HOME, MID, MINE
        )
    }
    Column {
        TestTabs(itemTitles = itemTitles, sel = selIndex)
        LCPager(modifier = Modifier.fillMaxSize(), pagerState)
    }
}
