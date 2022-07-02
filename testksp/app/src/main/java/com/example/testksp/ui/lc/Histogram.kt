package com.example.testksp.ui.lc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Histogram(
    modifier: Modifier = Modifier,
    items: SnapshotStateList<Pair<String, Double>>,
    itemColor: List<Color> = List(items.size) { Color(0xff123456) }
) {
    LazyColumn(modifier = modifier, content = {
        itemsIndexed(items) { index, it ->
            Row {
                Text(
                    text = it.first,
                    Modifier
                        .wrapContentWidth()
                        .padding(10.dp)
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .background(itemColor[index])
                        .fillMaxWidth((it.second / items.maxOf { it.second }).toFloat() * 0.5f)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = it.second.toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    })
}

@Preview
@Composable
fun HistogramPrev() {
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