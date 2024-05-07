package com.example.goalstracker.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.goalstracker.navigation.NavRoutes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Text(text = "hai")
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(NavRoutes.Scafold.route)
    }

}