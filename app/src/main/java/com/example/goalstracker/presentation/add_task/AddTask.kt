package com.example.goalstracker.presentation.add_task

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalstracker.AndroidNotificationScheduler
import com.example.goalstracker.R
import com.example.goalstracker.data.Notification
import com.example.goalstracker.data.Task
import com.example.goalstracker.presentation.components.TextDesign
import com.example.goalstracker.ui.theme.GoalsTrackerTheme
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(addTaskViewModel: AddTaskViewModel= hiltViewModel()) {
    var openDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {

        }
    )
    val isGranted = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.POST_NOTIFICATIONS
    ) == PackageManager.PERMISSION_GRANTED

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
                    txt = "Add new task",
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
                    top = 20.dp
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = text, modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(bottom = 20.dp),
                onValueChange = { text = it }, shape = RoundedCornerShape(15.dp),
                placeholder = {
                    TextDesign(
                        txt = "Enter task name",
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

            Row(
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .padding(bottom = 5.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = showTimePicker,
                    onCheckedChange = {
                        showTimePicker = it
                        if (!isGranted)
                            permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                    },
                    enabled = true, colors = CheckboxDefaults.colors(
                        uncheckedColor = Color.LightGray,
                        checkedColor = Color.Black,
                        disabledCheckedColor = Color.LightGray,
                        disabledUncheckedColor = Color.LightGray,
                        disabledIndeterminateColor = Color.LightGray
                    )
                )
                TextDesign(
                    txt = "do you want to set deadline for task?",
                    fs = 15,
                    fw = FontWeight.Medium
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
                        openDialog=true
                    else if (showTimePicker){
                        if (state.hour < localTime.hour) {
                            Toast.makeText(
                                context, "Please select time greater than present time",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else if(state.hour==localTime.hour && state.minute<=localTime.minute){
                            Toast.makeText(
                                context, "Please select time greater than present time",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            notificationScheduler.schedule(
                                notification = Notification(
                                    title = "Food is ready",
                                    description = "A courier is coming to you"
                                ), hr = state.hour.toLong() - localTime.hour.toLong(),
                                min = state.minute.toLong() - localTime.minute.toLong()
                            )
                        }
                        addTaskViewModel.addTask(Task(
                            tName=text,a=true,aHour=state.hour,aMinute=state.minute
                        ))

                    }else{
                        addTaskViewModel.addTask(Task(
                            tName = text
                        ))
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
                TextDesign(
                    txt = "save",
                    fs = 20,
                    fw = FontWeight.Bold,
                    color = Color.White
                )
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showSystemUi = true)
@Composable
private fun AddTaskPreview() {
    GoalsTrackerTheme {
        AddTaskScreen()
    }
}