package com.example.goalstracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.goalstracker.navigation.NavBarItems
import com.example.goalstracker.navigation.NavRoutes
import com.example.goalstracker.navigation.NavigationHost
import com.example.goalstracker.presentation.AddTaskScreen
import com.example.goalstracker.presentation.AlarmListScreen
import com.example.goalstracker.presentation.HomeScreen
import com.example.goalstracker.presentation.ProfileScreen
import com.example.goalstracker.ui.theme.GoalsTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoalsTrackerTheme {
                val navController = rememberNavController()
                NavigationHost(navController = navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Scafold(navController: NavHostController) {
    val navController1 = rememberNavController()
    val backStackEntry = navController1.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavBarItems.BarItems.forEach { navBarItem ->
                    val selected = navBarItem.route == backStackEntry.value?.destination?.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if(backStackEntry.value?.destination?.route!=navBarItem.route)
                            navController1.navigate(navBarItem.route) {
                                popUpTo(navController1.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
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
        val time = 300
        NavHost(
            navController = navController1,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = NavRoutes.Home.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(time, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = { fadeOut(animationSpec = tween(time-200, easing = FastOutSlowInEasing)) },
                popExitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None })
            {
                HomeScreen(progressPercentage = 0.4f)
            }
            composable(route = NavRoutes.Profile.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(time, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = { fadeOut(animationSpec = tween(time-200, easing = FastOutSlowInEasing)) },
                popExitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None })
            {
                ProfileScreen()
            }
            composable(route = NavRoutes.Alarm.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(time, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = { fadeOut(animationSpec = tween(time-200, easing = FastOutSlowInEasing)) },
                popExitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None })
            {
                AlarmListScreen()
            }
            composable(route = NavRoutes.AddTask.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(time, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = { fadeOut(animationSpec = tween(time-200, easing = FastOutSlowInEasing)) },
                popExitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None })
            {
                AddTaskScreen()
            }
        }
    }
}


/*
val ReorderItem = listOf(
    "Item 1",
    "Item 2",
    "Item 3",
    "Item 4",
    "Item 5",
    "Item 6",
    "Item 7",
    "Item 8",
    "Item 9",
    "Item 10",
    "Item 11",
    "Item 12",
    "Item 13",
    "Item 14",
    "Item 15",
    "Item 16",
    "Item 17",
    "Item 18",
    "Item 19",
    "Item 20"
).toMutableStateList()
@Composable
fun Greeting() {

    DragDropList(
        items = ReorderItem,
        onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex)}
    )

    LazyColumn {
        items(10) {
            Box(modifier = Modifier
                .height(40.dp)
                .padding(3.dp)
                .shadow(elevation = 2.dp)){
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Task Name")
                    Spacer(modifier = Modifier.width(40.dp))
                    Text(text = "DeadLine", textAlign = TextAlign.End)
                }
            }
        }
    }



}
*/

