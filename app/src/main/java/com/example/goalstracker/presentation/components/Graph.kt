package com.example.goalstracker.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.goalstracker.R

@Composable
fun Graph(map: List<Pair<String, Float>>, ht: Int) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(.8f)
            .height((ht + 160).dp), elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextDesign(
                    txt = "Weekly Overview",
                    fs = 20,
                    fw = FontWeight.SemiBold,
                    color = Color.Black
                )
                TextDesign(
                    txt = "Apr 10 - Apr 17",
                    fs = 15,
                    fw = FontWeight.Normal,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height((ht + 40).dp)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                map.forEach {

                    Column(
                        Modifier.padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var animationTriggered by remember {
                            mutableStateOf(false)
                        }
                        val graphHeight by animateFloatAsState(
                            targetValue = if (animationTriggered) it.second else 0f,
                            animationSpec = tween(
                                durationMillis = 1000,
                                delayMillis = 50
                            ), label = "anime"
                        )
                        LaunchedEffect(key1 = true) {
                            animationTriggered = true
                        }
                        Box(
                            modifier = Modifier
                                .width(28.dp)
                                .height((ht * graphHeight).dp)
                                .padding(2.dp)
                                .clip(
                                    RoundedCornerShape(15.dp)
                                )
                                .background(color = Color.Blue, shape = RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Box(modifier = Modifier.padding(top = 4.dp)) {
                                if (graphHeight == 1f) {
                                    Image(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_star_24),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                        TextDesign(
                            modifier = Modifier.padding(top = 2.dp),
                            txt = it.first,
                            fw = FontWeight.SemiBold,
                            color = Color.Gray
                        )

                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextDesign(
                    txt = "You are doing good!",
                    fw = FontWeight.SemiBold,
                    color = Color.Gray
                )
                TextDesign(
                    modifier = Modifier,
                    txt = "keep it up",
                    fs = 15,
                    fw = FontWeight.Normal,
                    color = Color.Gray
                )
            }


        }
    }

}