package com.example.goalstracker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goalstracker.Scafold
import com.example.goalstracker.presentation.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationHost(navController: NavHostController) {
    val time = 300
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {

        composable(route = NavRoutes.Splash.route,
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
            SplashScreen(navController = navController)
        }
        /*composable(route = NavRoutes.Home.route){
            HomeScreen(progressPercentage = 0.4f)
        }
        composable(route = NavRoutes.Profile.route){
            ProfileScreen()
        }
        composable(route = NavRoutes.Alarm.route){
            AlarmListScreen()
        }
        composable(route = NavRoutes.AddTask.route){
            AddTaskScreen()
        }
        composable(route = NavRoutes.SignUp.route){
            SignUpScreen()
        }*/
        composable(route = NavRoutes.Scafold.route) {
            Scafold(navController)
        }
    }
}