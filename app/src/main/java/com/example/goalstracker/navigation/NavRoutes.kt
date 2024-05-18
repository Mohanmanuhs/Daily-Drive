package com.example.goalstracker.navigation

import kotlinx.serialization.Serializable



@Serializable
sealed class NavRoutes {
    @Serializable
    data object Home : NavRoutes()

    @Serializable
    data object Scafold : NavRoutes()

    @Serializable
    data object Splash : NavRoutes()

    @Serializable
    data object Profile : NavRoutes()

    @Serializable
    data object Alarm : NavRoutes()

    @Serializable
    data object AddTask :NavRoutes()

    @Serializable
    data object SignUp : NavRoutes()

}
