package com.example.goalstracker.notify

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_BOOT_COMPLETED
import com.example.goalstracker.data.TaskRepository
import com.example.goalstracker.data.WeeklyRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var appNotificationManager: AppNotificationManager

    @Inject
    lateinit var repository: TaskRepository

    @Inject
    lateinit var weeklyRepository: WeeklyRepository
    override fun onReceive(context: Context, intent: Intent?) {

        if (intent != null) {
            when(intent.action) {
                ACTION_NOTIFICATION -> {
                    val title = intent.getStringExtra("TITLE") ?: return
                    val description = intent.getStringExtra("DESCRIPTION") ?: return
                    val tName = intent.getStringExtra("tName") ?: return
                    appNotificationManager
                        .showBasicNotification(
                            title = title,
                            description = description
                        )
                    repository.offAlarm(tName)
                }
                ACTION_SUNDAY_TASK -> {
                    val androidNotificationScheduler = AndroidNotificationScheduler(context)
                    weeklyRepository.sundayClear()
                    androidNotificationScheduler.setSundayEndAlarm()
                }
                ACTION_BOOT_COMPLETED -> {
                    val androidNotificationScheduler = AndroidNotificationScheduler(context)
                    androidNotificationScheduler.setSundayEndAlarm()
                }
            }
        }
    }
    companion object {
        const val ACTION_NOTIFICATION = "com.example.app.ACTION_NOTIFICATION"
        const val ACTION_SUNDAY_TASK = "com.example.app.ACTION_SUNDAY_TASK"
    }
}