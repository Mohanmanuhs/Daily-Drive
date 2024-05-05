package com.example.goalstracker.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextDesign(txt: String, modifier: Modifier=Modifier, fs:Int=16, fw:FontWeight, color:Color=Color.Black) {
    Text(
        text = txt,
        modifier=Modifier.then(modifier),
        fontSize = fs.sp,
        fontWeight = fw,
        color = color
    )
}