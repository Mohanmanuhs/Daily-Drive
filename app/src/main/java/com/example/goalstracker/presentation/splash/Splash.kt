package com.example.goalstracker.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.goalstracker.R
import com.example.goalstracker.navigation.NavRoutes
import com.example.goalstracker.pref.SharedPref
import com.example.goalstracker.ui.theme.txtColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPrefs = SharedPref(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .size(150.dp, 150.dp)
                .clip(shape = RoundedCornerShape(25.dp))
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.app_logo),
                contentDescription = "logo"
            )
        }
        Text(
            text = "D-Drive",
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            color= txtColor
        )
    }
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        if(sharedPrefs.welcome){
            navController.navigate(NavRoutes.SignUp) {
                popUpTo(0)
            }
            sharedPrefs.wel()
        }else{
            navController.navigate(NavRoutes.Scafold) {
                popUpTo(0)
            }
        }
    }
}