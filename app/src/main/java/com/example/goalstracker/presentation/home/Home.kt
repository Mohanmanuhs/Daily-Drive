package com.example.goalstracker.presentation.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalstracker.pref.SharedPref
import com.example.goalstracker.ui.theme.rowBgColor
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val sharedPrefs = SharedPref(context)
    val dialogShownToday = remember { mutableStateOf(false) }
    val todayDateString = LocalDate.now().toString()
    val dialogShownTodayPref = sharedPrefs.getTodayBoolean(todayDateString)
    val completed = sharedPrefs.getCompleted().toFloat()
    val total = sharedPrefs.getTotal().toFloat()
    homeViewModel.updateWeekly(if (completed == 0f) 0f else completed / total)

    LaunchedEffect(key1 = Unit) {
        if (!dialogShownTodayPref && !dialogShownToday.value) {
            delay(TimeUnit.SECONDS.toMillis(1))
            dialogShownToday.value = true
            sharedPrefs.putTodayBoolean(todayDateString)
        }
    }

    if (dialogShownToday.value) {
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = { dialogShownToday.value = false },
            text = {
                Text(
                    "Do you want to delete previous day tasks?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        homeViewModel.deleteAllTask()
                        sharedPrefs.deleteAll()
                        dialogShownToday.value = false
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogShownToday.value = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 1.dp
        )
    }

    val msgList = listOf("Persist", "Great")
    val list by homeViewModel.allTask.collectAsState(initial = emptyList())

    val completedListSize = list.count { it.tc }
    var taskPosition by remember {
        mutableIntStateOf(0)
    }

    val openDialog = remember { mutableStateOf(false) }


    if (openDialog.value) {
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = { openDialog.value = false },
            text = {
                Text(
                    "Do you want to delete this task?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        homeViewModel.deleteTask(taskPosition)
                        sharedPrefs.decrementTotal()
                        if (list.find { it.pos == taskPosition }?.tc == true) {
                            sharedPrefs.decrementCompleted()
                        }
                        openDialog.value = false
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            },
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 1.dp
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Today Performance",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Box(contentAlignment = Alignment.Center) {
            CircularProgress(completedListSize.toFloat() / list.size.toFloat())
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.padding(bottom = 0.dp),
                    text = "${completedListSize}/${list.size}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Text(
                    text = if (completedListSize.toFloat() / list.size.toFloat() > 0.6f) msgList[1] else msgList[0],
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, bottom = 10.dp)
        ) {
            Text(text = "Tasks : -", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = list, key = { it.pos }) { task ->
                var expanded by remember { mutableStateOf(false) }
                var taskClickPosition by remember {
                    mutableStateOf(DpOffset.Zero)
                }

                Box(
                    modifier = Modifier
                        .animateItem(tween(1000))
                        .padding(bottom = 10.dp)
                        .clip(shape = RoundedCornerShape(18.dp))
                        .background(rowBgColor)
                        .pointerInput(true) {
                            detectTapGestures(onLongPress = { offset ->
                                taskClickPosition =
                                    DpOffset(x = offset.x.toDp(), y = offset.y.toDp() - 70.dp)
                                taskPosition = task.pos
                                expanded = true
                            })
                        }
                        .fillMaxWidth(0.85f), contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                            checked = task.tc,
                            onCheckedChange = {
                                if (it) {
                                    homeViewModel.taskCompleted(task.pos)
                                    sharedPrefs.incrementCompleted()
                                } else {
                                    homeViewModel.taskNotCompleted(
                                        task.pos
                                    )
                                    sharedPrefs.decrementCompleted()
                                }
                            },
                            enabled = true
                        )
                        if (task.tc) {
                            Text(
                                text = task.tName,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp, color = Color.Gray,
                                style = TextStyle(textDecoration = TextDecoration.LineThrough)
                            )
                        } else {
                            Text(
                                text = task.tName,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp
                            )
                        }
                        if (task.pTask) {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .rotate(30f),
                                imageVector = Icons.Filled.PushPin,
                                contentDescription = "edit"
                            )
                        }
                    }
                    DropdownMenu(
                        offset = taskClickPosition, expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = {
                                openDialog.value = true
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { if (task.pTask) Text("Unpin") else Text("Pin to top") },
                            onClick = {
                                if (task.pTask) homeViewModel.unPinTask(taskPosition) else homeViewModel.pinTask(
                                    taskPosition
                                )
                                expanded = false
                            },
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun CircularProgress(progressPercentage: Float) {
    var animationTriggered by remember {
        mutableStateOf(false)
    }
    val percentage by animateFloatAsState(
        targetValue = if (animationTriggered) if (progressPercentage.isNaN()) 0f else progressPercentage else 0f,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 50
        ), label = "anime"
    )
    LaunchedEffect(key1 = true) {
        delay(500)
        animationTriggered = true
    }
    Canvas(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
    ) {
        drawArc(
            size = Size(size.width, size.height),
            startAngle = 140f,
            sweepAngle = 260f,
            useCenter = false,
            style = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round),
            color = Color.LightGray
        )
        drawArc(
            size = Size(size.width, size.height),
            startAngle = 140f,
            sweepAngle = percentage * 260f,
            useCenter = false,
            style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round),
            color = Color.Blue
        )
        val angleInDegrees = (percentage * 260.0) + 50.0
        val radius = (size.height / 2)
        val x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        val y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)
        drawCircle(
            color = Color.Blue,
            radius = 35f,
            center = Offset(x, y)
        )
        drawCircle(
            color = Color.White,
            radius = 15f,
            center = Offset(x, y)
        )
    }
}