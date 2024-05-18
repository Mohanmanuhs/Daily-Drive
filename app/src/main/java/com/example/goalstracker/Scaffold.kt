package com.example.goalstracker

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
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.goalstracker.navigation.NavBarItems
import com.example.goalstracker.navigation.NavRoutes
import com.example.goalstracker.presentation.add_task.AddTaskScreen
import com.example.goalstracker.presentation.alarm_list.AlarmListScreen
import com.example.goalstracker.presentation.home.HomeScreen
import com.example.goalstracker.presentation.profile.ProfileScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Scaffold() {
    val navController1 = rememberNavController()
    val backStackEntry by navController1.currentBackStackEntryAsState()
    val currentDestination  = backStackEntry?.destination
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavBarItems.BarItems.forEach { navBarItem ->
                    val selected = currentDestination == navBarItem.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (currentDestination != navBarItem.route) {
                                navController1.navigate(navBarItem.route) {
                                    popUpTo(navController1.graph.findStartDestination().id){
                                        saveState=true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navBarItem.image,
                                contentDescription = navBarItem.title
                            )
                        }, label = { Text(text = navBarItem.title) }
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
                AlarmListScreen()
            }
            composable<NavRoutes.AddTask>
            {
                AddTaskScreen()
            }
        }
    }
}
