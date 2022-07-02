package com.example.testksp.ui.pages

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testksp.ui.TestGrid
import com.example.testksp.ui.lc.Histogram
import com.example.testksp.ui.nav.MINE

@Composable
fun Mine() {
    Column(
        Modifier.scrollable(
            rememberScrollableState(consumeScrollDelta = { it }),
            orientation = Orientation.Vertical
        )
    ) {
        Text(
            text = MINE,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )
        TestGrid(items = remember {
            mutableStateListOf(
                "1" to "2",
                "a" to "b",
                "text" to "count",
                "title" to "details",
                "elements" to "100"
            )
        })

        Histogram(items = remember {
            mutableStateListOf(
                "yufw" to 10.0,
                "xcxn" to 20.0,
                "llmxd" to 30.0,
                "hvpu" to 40.0,
                "tgxp " to 50.0,
                "xcn" to 60.0,
                "hwwz" to 70.0,
                "oppo" to 80.0,
                "vivio" to 90.0,
            )
        })
    }
}