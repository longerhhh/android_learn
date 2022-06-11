package com.example.composetest.ui

import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

/**
 * FileName: ListScreen
 * create date: 2022/5/11
 *
 * @author: longer
 * @description: 列表ui
 */

const val TAG = "ListScreen"

//@ExperimentalFoundationApi
@Composable
fun ListScreen() {
    val checked = remember {
        mutableStateOf(true)
    }
    LazyColumn(content = {
        item {
            TestItemSwitch(checked)
        }
        item {
            TestItemButton()
        }
        item {
//            LazyVerticalGrid(cells = GridCells.Adaptive(30.dp), content = {
//                repeat(10) {
//                    item {
//                        TextButton(onClick = { Log.d(TAG, "ListScreen: onclick") }) {
//                            Text(
//                                text = "wo",
//                                Modifier.background(Color.Gray, RoundedCornerShape(15.dp))
//                            )
//                        }
//                    }
//                }
//            })
        }
    })
}

@Composable
fun TestItemButton() {
    
    
    IconButton(onClick = { /*TODO*/ }) {
//        Image(painter = painterResource(id = drawable), contentDescription = )
    }
}

@Composable
private fun TestItemSwitch(checked: MutableState<Boolean>) {
    val refreshState = remember {
        SwipeRefreshState(false)
    }
    SwipeRefresh(state = refreshState, onRefresh = { }) {
        
        
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            modifier = Modifier
                .requiredHeight(100.dp)
                .requiredWidth(200.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.White,
                checkedTrackColor = Color.Blue,
                uncheckedTrackColor = Color.Gray
            )
        )
    }
    
}

@Preview
@Composable
fun DefaultPreview() {
    ListScreen()
}