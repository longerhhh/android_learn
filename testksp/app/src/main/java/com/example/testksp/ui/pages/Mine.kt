package com.example.testksp.ui.pages

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testksp.ui.default.DefaultHistogram
import com.example.testksp.ui.default.DefaultTestGrid
import com.example.testksp.ui.default.DefaultTitle
import com.example.testksp.ui.nav.MINE

@Composable
fun Mine() {
    Column(
        Modifier.scrollable(
            rememberScrollableState(consumeScrollDelta = { it }),
            orientation = Orientation.Vertical
        )
    ) {
        DefaultTitle(MINE)
        DefaultTestGrid()
        DefaultHistogram()
    }
}
