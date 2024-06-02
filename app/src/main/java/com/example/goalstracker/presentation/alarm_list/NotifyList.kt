package com.example.goalstracker.presentation.alarm_list

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalstracker.presentation.components.DownsideCurveCutBackground
import com.example.goalstracker.ui.theme.GoalsTrackerTheme
import com.example.goalstracker.ui.theme.rowBgColor

@Composable
fun NotifyList(notifyViewModel: NotifyViewModel = hiltViewModel()) {
    val list by notifyViewModel.allTask.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            DownsideCurveCutBackground()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Upcoming",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = "Notifications",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = "List",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(list.filter { it.a }, key = { it.pos }) { task ->
                Box(
                    modifier = Modifier
                        .animateItem(tween(1000))
                        .padding(bottom = 10.dp)
                        .clip(shape = RoundedCornerShape(18.dp))
                        .background(rowBgColor)
                        .fillMaxWidth(0.85f), contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                            imageVector = Icons.Filled.NotificationsActive,
                            contentDescription = "Notifications"
                        )
                        Text(text = task.tName, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                        Box(
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(18.dp))
                                .height(70.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = "${task.aHour}:${task.aMinute} ${if (task.aAm == 1) "AM" else "PM"}",
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AlarmListPreview() {
    GoalsTrackerTheme {
        NotifyList()
    }
}