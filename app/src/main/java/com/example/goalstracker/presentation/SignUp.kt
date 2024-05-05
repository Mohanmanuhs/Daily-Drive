package com.example.goalstracker.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.goalstracker.R
import com.example.goalstracker.presentation.components.TextDesign

@Composable
fun SignUpScreen() {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.group_28), contentScale = ContentScale.Crop, contentDescription = "")
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextDesign(
                    txt = "Welcome !",
                    fs=40,
                    fw = FontWeight.SemiBold,
                    color = Color.White
                )
                TextDesign(
                    txt = "to",
                    fs=40,
                    fw = FontWeight.SemiBold,
                    color = Color.White
                )

                TextDesign(
                    txt = "Daily-Drive",
                    fs=40,
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
                    top = 20.dp
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var text by rememberSaveable { mutableStateOf("") }
            var pass by rememberSaveable { mutableStateOf("") }
            var showPassword by remember { mutableStateOf(value = false) }
            OutlinedTextField(
                value = text, modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(bottom = 20.dp),
                onValueChange = { text = it }, shape = RoundedCornerShape(15.dp),
                placeholder = {

                    TextDesign(
                        txt = "Enter your name",
                        modifier = Modifier.padding(start = 5.dp),
                        fw = FontWeight.Light,
                        color = Color(0xFF929292)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF3F1F1),
                    focusedContainerColor = Color(0xFFF3F1F1),
                    unfocusedBorderColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray
                )
            )
            OutlinedTextField(
                value = pass,
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(bottom = 20.dp),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { if (it.isDigitsOnly()) pass = it },
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    TextDesign(
                        txt = "Enter 4 digit password",
                        modifier = Modifier.padding(start = 5.dp),
                        fw = FontWeight.Light,
                        color = Color(0xFF929292)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF3F1F1),
                    focusedContainerColor = Color(0xFFF3F1F1),
                    unfocusedBorderColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray
                )
            )
            val checkedState = remember { mutableStateOf(true) }
            Row(
                modifier = Modifier
                    .fillMaxWidth(.75f)
                    .padding(bottom = 30.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
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
                TextDesign(
                    txt = "I agree for ",
                    fs = 15,
                    fw = FontWeight.Medium,
                )
                TextDesign(
                    txt = "terms and conditions.",
                    fs = 15,
                    fw = FontWeight.Medium,
                    color = Color.Red
                )

            }
            Button(
                onClick = {
                    /* Do something! */
                },
                modifier = Modifier
                    .width(140.dp)
                    .height(50.dp),
                enabled = true,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFA5D5D),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                TextDesign(
                    txt = "Let's Go!",
                    fs = 20,
                    fw = FontWeight.Bold,
                    color = Color.White
                )
            }

        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Coming() {
    SignUpScreen()
}