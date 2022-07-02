package com.example.testksp.ui.pages

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
import com.example.testksp.ui.data.TreeBean
import com.example.testksp.ui.lc.TestTree
import com.example.testksp.ui.nav.HOME

@Composable
fun Home() {
    Column {
        Text(
            text = HOME,
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
}