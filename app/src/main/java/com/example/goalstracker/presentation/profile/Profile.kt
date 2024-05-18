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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goalstracker.R
import com.example.goalstracker.presentation.components.Graph
import com.example.goalstracker.presentation.components.TextDesign
import com.example.goalstracker.ui.theme.GoalsTrackerTheme

@Composable
fun ProfileScreen() {
    var text by rememberSaveable { mutableStateOf("Mohan") }
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
            Box(modifier= Modifier
                .fillMaxWidth(.8f)
                .padding(5.dp), contentAlignment = Alignment.TopStart){
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
            Graph(map = listOf(
                "M" to 1f,
                "T" to .2f,
                "W" to .8f,
                "T" to .3f,
                "F" to .9f,
                "S" to .5f,
                "S" to .9f
            ), ht = 150)



        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ProfileViewer() {
    GoalsTrackerTheme {
        ProfileScreen()
    }
}