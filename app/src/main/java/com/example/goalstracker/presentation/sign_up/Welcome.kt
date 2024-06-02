package com.example.goalstracker.presentation.sign_up

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.goalstracker.R
import com.example.goalstracker.data.Weekly
import com.example.goalstracker.navigation.NavRoutes
import com.example.goalstracker.notify.AndroidNotificationScheduler
import com.example.goalstracker.pref.SharedPref
import com.example.goalstracker.presentation.components.DownsideCurveCutBackground
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val todayDateString = LocalDate.now().toString()
    val sharedPrefs = SharedPref(context)
    val androidNotificationScheduler = AndroidNotificationScheduler(context)

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {

        }
    )

    LaunchedEffect(key1 = Unit) {
        if (sharedPrefs.isFirstTime) {
            sharedPrefs.isFirstTime()
            welcomeViewModel.addWeekly(Weekly())
            androidNotificationScheduler.setSundayEndAlarm()
            sharedPrefs.putTodayBoolean(todayDateString)
        }
        val isGranted =
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        if (!isGranted)
            permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            DownsideCurveCutBackground()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome !",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = "to",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Text(
                    text = "Daily-Drive",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(.78f),
                painter = painterResource(id = R.drawable.designer),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
            Button(
                onClick = {
                    navController.navigate(NavRoutes.Scafold)
                },
                modifier = Modifier
                    .width(140.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFA5D5D)
                )
            ) {
                Text(
                    text = "Let's Go!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

        }
    }

}