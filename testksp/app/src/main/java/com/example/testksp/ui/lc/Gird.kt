package com.example.testksp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testksp.ui.base.BaseTextModifier

@Composable
fun TestGrid(items: SnapshotStateList<Pair<String, String>>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), content = {
        items(items = items) {
            GridItem(it.first, it.second)
        }
    })
}

@Composable
fun GridItem(title: String, content: String) {
    Column {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = BaseTextModifier.padding(vertical = 10.dp).fillMaxWidth()
        )
        Text(
            text = content,
            textAlign = TextAlign.Center,
            modifier = BaseTextModifier.padding(bottom = 10.dp).fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun GirdPreview() {
    TestGrid(items = remember {
        mutableStateListOf(
            "1" to "2",
            "a" to "b",
            "text" to "count",
            "title" to "details",
            "elements" to "100"
        )
    })
}