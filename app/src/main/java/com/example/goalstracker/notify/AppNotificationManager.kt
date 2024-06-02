package com.example.goalstracker.notify

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.goalstracker.R
import kotlin.random.Random

class AppNotificationManager(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showBasicNotification(
        title: String,
        description: String
    ) {
        val notification = NotificationCompat.Builder(context, "task_notification")
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(Random.nextInt(), notification)
    }
}