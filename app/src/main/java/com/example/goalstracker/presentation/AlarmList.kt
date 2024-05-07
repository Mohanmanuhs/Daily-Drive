package com.example.goalstracker.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goalstracker.R
import com.example.goalstracker.presentation.components.TextDesign
import com.example.goalstracker.ui.theme.GoalsTrackerTheme

@Composable
fun AlarmListScreen() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.vector_2),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextDesign(
                    txt = "Upcoming",
                    fs = 40,
                    fw = FontWeight.SemiBold,
                    color = Color.White
                )
                TextDesign(
                    txt = "Alarms",
                    fs = 40,
                    fw = FontWeight.SemiBold,
                    color = Color.White
                )
                TextDesign(
                    txt = "List",
                    fs = 40,
                    fw = FontWeight.SemiBold,
                    color = Color.White
                )

            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(
                    top = 10.dp
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(0.85f).clip(shape = RoundedCornerShape(18.dp)).background(color = Color(0xFFD9D9D9)), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp),
                    painter = painterResource(id = R.drawable.baseline_alarm_on_24),
                    contentDescription = ""
                )
                TextDesign(txt = "Task Name", fw = FontWeight.Normal, fs = 20)
                Box(
                    modifier = Modifier.padding(end=15.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(18.dp))
                        .height(70.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    TextDesign(txt = "9:20 Am", fw = FontWeight.Normal, fs = 20)


                }
            }


        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AlarmListPreview() {
    GoalsTrackerTheme {
        AlarmListScreen()
    }
}