package com.example.testksp.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testksp.R
import com.example.testksp.ui.pages.Home
import com.example.testksp.ui.pages.Mid
import com.example.testksp.ui.pages.Mine

@Composable
fun MainNav() {
    Column(Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HOME) {
            composable(HOME) {
                Home()
            }
            composable(MID) {
                Mid()
            }
            composable(MINE) {
                Mine()
            }
        }
        BottomNav(navController)
    }
}

@Composable
private fun BottomNav(navController: NavHostController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        var sel = remember {
            HOME
        }
        val barList = listOf(
//        HOME to androidx.appcompat.R.drawable.abc_ic_go_search_api_material,
//        MID to androidx.appcompat.R.drawable.abc_btn_check_material,
//        MINE to com.google.android.material.R.drawable.abc_btn_radio_to_on_mtrl_015
            HOME to R.drawable.bone,
            MID to R.drawable.beach_umbrella,
            MINE to R.drawable.chicken
        )
        BottomNavigation(backgroundColor = Color.LightGray) {
            barList.forEachIndexed { index, pair ->
                val title = pair.first
                BottomNavigationItem(selected = sel == title, onClick = {
                    sel = title
                    navController.navigate(title)
                }, icon = {
                    Image(painter = painterResource(id = pair.second), contentDescription = "")
                }, label = {
                    Text(text = title)
                })
            }
        }
    }
}

@Composable
fun TestScaffold() {
    Scaffold(Modifier, topBar = {
        Text(text = "TestScaffold")
    }, bottomBar = {
        Text(text = "TestBottomBar") //
    }, drawerContent = {
        Text(text = "TestDrawerContent")
    }) {
        Box(Modifier.padding(it)) {

        }
    }
}

@Preview
@Composable
fun MainNavPrev() {
    MainNav()
}