package com.example.goalstracker.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalstracker.R
import com.example.goalstracker.presentation.components.Graph
import com.example.goalstracker.presentation.components.TextDesign

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = hiltViewModel()) {
    var text by rememberSaveable { mutableStateOf("Mohan") }
    LaunchedEffect(key1 = true) {
        profileViewModel.getWeeklyRepo()
    }
    val weekly by profileViewModel.weekly.collectAsState()

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
                    txt = "Profile",
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
            Box(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(5.dp), contentAlignment = Alignment.TopStart
            ) {
                TextDesign(txt = "Name : -", fw = FontWeight.SemiBold)
            }
            OutlinedTextField(
                value = text, modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(bottom = 0.dp),
                onValueChange = { text = it }, shape = RoundedCornerShape(15.dp),
                placeholder = {
                    TextDesign(
                        txt = "",
                        modifier = Modifier.padding(start = 5.dp),
                        fw = FontWeight.Light,
                        color = Color(0xFF929292)
                    )
                }, readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF3F1F1),
                    focusedContainerColor = Color(0xFFF3F1F1),
                    unfocusedBorderColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray
                )
            )
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