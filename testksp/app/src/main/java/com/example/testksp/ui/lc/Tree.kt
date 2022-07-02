package com.example.testksp.ui.lc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testksp.ui.data.TreeBean

@Composable
fun TestTree(items: SnapshotStateList<TreeBean>) {
    val showItemIndexList = remember {
        mutableListOf<Int>()
    }
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(), content = {
        itemsIndexed(items) { index, item ->
            TreeItem(item, modifier = Modifier.clickable {
                if (showItemIndexList.contains(index)) {
                    showItemIndexList.remove(index)
                    item.isShow.value = false
                } else {
                    showItemIndexList.add(index)
                    item.isShow.value = true
                }
            })
        }
    })
}

@Composable
fun TreeItem(bean: TreeBean, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = bean.title,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        if (bean.isShow.value) {
            TreeDetails(bean)
//            TreeDetailsLazyColumn(bean = bean)
        }
    }
}

@Composable
private fun TreeDetails(
    bean: TreeBean,
) {
    bean.details.forEach {
        Row(modifier = Modifier.padding(start = 15.dp)) {
            Text(
                text = it.first,
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(
                text = it.second.toString(),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

@Composable
fun TreeDetailsLazyColumn(    bean: TreeBean, ) {
    LazyColumn(content = {
        items(bean.details) {
            Row(modifier = Modifier) {
                Text(
                    text = it.first,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    text = it.second.toString(),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .padding(horizontal = 10.dp)
                )
            }
        }
    })
}

@Preview
@Composable
fun TreePreview() {
    TestTree(items = remember {
        mutableStateListOf(
            TreeBean(
                "根节点", listOf(
                    "yufw" to 100,
                    "xcxn" to 200,
                    "llmxd" to 300,
                    "hvpu" to 400,
                    "tgxp " to 500,
                    "xcn" to 600,
                    "hwwz" to 700,
                    "oppo" to 800,
                    "vivio" to 900,
                )
            ),
            TreeBean(
                "根节点", listOf(
                    "yufw" to 100,
                    "xcxn" to 200,
                    "llmxd" to 300,
                    "hvpu" to 400,
                    "tgxp " to 500,
                    "xcn" to 600,
                    "hwwz" to 700,
                    "oppo" to 800,
                    "vivio" to 900,
                )
            ),
            TreeBean(
                "根节点", listOf(
                    "yufw" to 100,
                    "xcxn" to 200,
                    "llmxd" to 300,
                    "hvpu" to 400,
                    "tgxp " to 500,
                    "xcn" to 600,
                    "hwwz" to 700,
                    "oppo" to 800,
                    "vivio" to 900,
                )
            ),
            TreeBean(
                "根节点", listOf(
                    "yufw" to 100,
                    "xcxn" to 200,
                    "llmxd" to 300,
                    "hvpu" to 400,
                    "tgxp " to 500,
                    "xcn" to 600,
                    "hwwz" to 700,
                    "oppo" to 800,
                    "vivio" to 900,
                )
            ),
            TreeBean(
                "根节点", listOf(
                    "yufw" to 100,
                    "xcxn" to 200,
                    "llmxd" to 300,
                    "hvpu" to 400,
                    "tgxp " to 500,
                    "xcn" to 600,
                    "hwwz" to 700,
                    "oppo" to 800,
                    "vivio" to 900,
                )
            ),
        )
    })
}