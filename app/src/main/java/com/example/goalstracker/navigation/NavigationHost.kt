package com.example.goalstracker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goalstracker.Scaffold
import com.example.goalstracker.presentation.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash
    ) {

        composable<NavRoutes.Splash>
        {
            SplashScreen(navController = navController)
        }
        composable<NavRoutes.Scafold> {
            Scaffold()
        }
    }
}