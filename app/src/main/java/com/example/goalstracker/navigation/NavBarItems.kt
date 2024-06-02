package com.example.goalstracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationAdd
import androidx.compose.material.icons.filled.Person

object NavBarItems {
    val BarItems = listOf(
        NavBarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = NavRoutes.Home
        ),
        NavBarItem(
            title = "Add",
            image = Icons.Filled.AddCircle,
            route = NavRoutes.AddTask
        ),
        NavBarItem(
            title = "Notify",
            image = Icons.Filled.NotificationAdd,
            route = NavRoutes.Alarm
        ),
        NavBarItem(
            title = "Profile",
            image = Icons.Filled.Person,
            route = NavRoutes.Profile
        )
    )


}