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
            route = "home_screen"
        ),
        NavBarItem(
            title = "Add",
            image = Icons.Filled.Add,
            route = "add_task_screen"
        ),
        NavBarItem(
            title = "Alarms",
            image = Icons.Filled.AlarmOn,
            route = "alarm_screen"
        ),
        NavBarItem(
            title = "Profile",
            image = Icons.Filled.Person,
            route = "profile_screen"
        )
    )


}