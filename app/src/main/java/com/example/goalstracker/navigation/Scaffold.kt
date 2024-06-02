package com.example.goalstracker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goalstracker.presentation.add_task.AddTaskScreen
import com.example.goalstracker.presentation.alarm_list.NotifyList
import com.example.goalstracker.presentation.home.HomeScreen
import com.example.goalstracker.presentation.profile.ProfileScreen
import com.example.goalstracker.ui.theme.txtColor


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Scaffold() {
    val navController1 = rememberNavController()
    var selected:NavRoutes by remember{
        mutableStateOf(NavRoutes.Home)
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavBarItems.BarItems.forEach { navBarItem ->
                    NavigationBarItem(
                        selected = selected==navBarItem.route,
                        onClick = {
                            if (selected != navBarItem.route) {
                                selected=navBarItem.route
                                navController1.navigate(navBarItem.route) {
                                    popUpTo(0)
                                    launchSingleTop = true
                                }
                            }
                        }, alwaysShowLabel = false,
                        icon = {
                            Icon(
                                imageVector = navBarItem.image,
                                contentDescription = navBarItem.title,
                                tint = if(selected==navBarItem.route) txtColor else Color.Gray
                            )
                        },
                        label = { Text(text = navBarItem.title, color = txtColor)}
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController1,
            startDestination = NavRoutes.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<NavRoutes.Home>
            {
                HomeScreen()
            }
            composable<NavRoutes.Profile>
            {
                ProfileScreen()
            }
            composable<NavRoutes.Alarm>
            {
                NotifyList()
            }
            composable<NavRoutes.AddTask>
            {
                AddTaskScreen()
            }
        }
    }
}
