package com.example.goalstracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AlarmOn
import androidx.compose.material.icons.filled.Home
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
            image = Icons.Filled.Add,
            route = NavRoutes.AddTask
        ),
        NavBarItem(
            title = "Alarms",
            image = Icons.Filled.AlarmOn,
            route = NavRoutes.Alarm
        ),
        NavBarItem(
            title = "Profile",
            image = Icons.Filled.Person,
            route = NavRoutes.Profile
        )
    )


}