package com.example.goalstracker.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalstracker.presentation.components.DownsideCurveCutBackground
import com.example.goalstracker.presentation.components.Graph

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        profileViewModel.getWeeklyRepo()
    }
    val weekly by profileViewModel.weekly.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            DownsideCurveCutBackground()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Profile",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Graph(
                map = listOf(
                    "M" to weekly.monday,
                    "T" to weekly.tuesday,
                    "W" to weekly.wednesday,
                    "T" to weekly.thursday,
                    "F" to weekly.friday,
                    "S" to weekly.saturday,
                    "S" to weekly.sunday
                ), ht = 150
            )
        }
    }
}