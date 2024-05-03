package com.example.goalstracker.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goalstracker.R
import com.example.goalstracker.ui.theme.GoalsTrackerTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HomeScreen(progressPercentage: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top=20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Today Performance",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Box(contentAlignment = Alignment.Center) {
            CircularProgress()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.padding(bottom = 0.dp),
                    text = "5/10",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Text(text = "Persist", fontWeight = FontWeight.Medium, fontSize = 20.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        ) {
            Text(text = "Task List : -", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        }
        TaskRowDesign()
    }
}

@Composable
fun TaskRowDesign(tName: String = "Task Name") {
    val checkedState = remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth(0.85f), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(18.dp))
                .height(70.dp)
                .background(color = Color(0xFFD9D9D9)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                modifier = Modifier.padding(start = 5.dp,end=5.dp),
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                enabled = true, colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.LightGray,
                    checkedColor = Color.Black,
                    disabledCheckedColor = Color.LightGray,
                    disabledUncheckedColor = Color.LightGray,
                    disabledIndeterminateColor = Color.LightGray
                )
            )
            Text(text = tName, fontWeight = FontWeight.Normal, fontSize = 20.sp)

        }
        Icon(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 15.dp),painter = painterResource(id = R.drawable.back), contentDescription = "")
    }
}

@Composable
fun CircularProgress(progressPercentage: Float = 1f) {
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
            sweepAngle = progressPercentage * 260f,
            useCenter = false,
            style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round),
            color = Color.Blue
        )
        var angleInDegrees = (progressPercentage * 260.0) + 50.0
        var radius = (size.height / 2)
        var x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        var y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)
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

@Preview(showSystemUi = true)
@Composable
private fun Home() {
    GoalsTrackerTheme {
        HomeScreen(.5f)
    }
}