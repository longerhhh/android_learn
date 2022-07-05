package com.example.testksp.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.testksp.ui.default.DefaultTestGrid
import com.example.testksp.ui.default.DefaultTestTree
import com.example.testksp.ui.default.DefaultTitle
import com.example.testksp.ui.nav.HOME

@Composable
fun Home() {
    Column {
        DefaultTitle(title = HOME)
        DefaultTestGrid()
        DefaultTestTree()
    }
}
