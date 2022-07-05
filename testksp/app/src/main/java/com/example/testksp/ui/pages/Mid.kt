package com.example.testksp.ui.pages

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testksp.ui.default.DefaultHistogram
import com.example.testksp.ui.default.DefaultTestTree
import com.example.testksp.ui.default.DefaultTitle
import com.example.testksp.ui.nav.MID

@Composable
fun Mid() {
    Column(
        Modifier.scrollable(
            rememberScrollableState(consumeScrollDelta = { it }),
            orientation = Orientation.Vertical
        )
    ) {
        DefaultTitle(title = MID)
        DefaultHistogram()
        DefaultTestTree()
    }
}
