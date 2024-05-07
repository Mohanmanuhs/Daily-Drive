package com.example.goalstracker.navigation

sealed class NavRoutes(val route: String) {
    data object Scafold : NavRoutes("scafold_screen")
    data object Home : NavRoutes("home_screen")
    data object Splash : NavRoutes("splash_screen")
    data object Profile : NavRoutes("profile_screen")
    data object Alarm : NavRoutes("alarm_screen")
    data object AddTask : NavRoutes("add_task_screen")
    data object SignUp : NavRoutes("signup_screen")
}
