package com.example.goalstracker.presentation.add_task

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalstracker.data.Notification
import com.example.goalstracker.data.Task
import com.example.goalstracker.notify.AndroidNotificationScheduler
import com.example.goalstracker.pref.SharedPref
import com.example.goalstracker.presentation.components.DownsideCurveCutBackground
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(addTaskViewModel: AddTaskViewModel = hiltViewModel()) {
    var openDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPref = SharedPref(context)
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { openDialog = false },
            icon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info") },
            title = {
                Text(text = "Permissions Request")
            },
            text = {
                Text(
                    "Please give permission to notifications"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        ).also {
                            context.startActivity(it)
                        }
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text("CANCEL")
                }
            }
        )
    }
    val localTime = LocalTime.now()
    var text by rememberSaveable { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }
    val state = rememberTimePickerState()
    val snackState = remember { SnackbarHostState() }
    val notificationScheduler = remember {
        AndroidNotificationScheduler(context)
    }
    val isGranted by remember{
        mutableStateOf(ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED)
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            DownsideCurveCutBackground()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Add new task",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
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
            OutlinedTextField(
                value = text, modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(bottom = 20.dp),
                onValueChange = { text = it }, shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text(
                        text = "Enter task name",
                        modifier = Modifier.padding(start = 5.dp),
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF929292)
                    )
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .padding(bottom = 5.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = showTimePicker,
                    onCheckedChange = {
                        showTimePicker = it
                    },
                    enabled = true
                )
                Text(
                    text = "do you want to set remainder for task?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Column {
                Box(propagateMinConstraints = false) {
                    SnackbarHost(hostState = snackState)
                }
                Spacer(modifier = Modifier.height(3.dp))
                if (showTimePicker) {
                    TimeInput(
                        modifier = Modifier,
                        state = state
                    )
                }
            }
            Button(
                onClick = {

                    if (!isGranted)
                        openDialog = true
                    else if (showTimePicker) {
                        if (state.hour < localTime.hour || (state.hour == localTime.hour && state.minute <= localTime.minute)) {
                            Toast.makeText(
                                context, "Please select time greater than present time",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            addTaskViewModel.addTask(
                                Task(
                                    tName = text,
                                    a = true,
                                    aHour = if (state.hour == 0) 12 else if (state.hour > 12) state.hour - 12 else state.hour,
                                    aMinute = state.minute,
                                    if (state.hour < 12) 1 else 0
                                )
                            )
                            sharedPref.incrementTotal()
                            notificationScheduler.schedule(
                                notification = Notification(
                                    title = "Task Remainder",
                                    description = "$text task is remaining please complete it"
                                ), tName = text, hr = state.hour.toLong() - localTime.hour.toLong(),
                                min = state.minute.toLong() - localTime.minute.toLong()
                            )
                        }
                    } else {
                        addTaskViewModel.addTask(
                            Task(
                                tName = text
                            )
                        )
                        sharedPref.incrementTotal()
                    }
                },
                modifier = Modifier
                    .width(140.dp)
                    .height(50.dp),
                enabled = true,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFA5D5D)
                )
            ) {
                Text(
                    text = "save",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }

}