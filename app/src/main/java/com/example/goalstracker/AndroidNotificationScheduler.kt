package com.example.goalstracker

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.goalstracker.data.Notification
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import kotlin.random.Random


class AndroidNotificationScheduler(private val context: Context):NotificationScheduler {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    
    override fun schedule(notification: Notification,tName:String, hr:Long, min:Long) {
        val intent = Intent(context, NotificationReceiver::class.java)
            .apply {
                putExtra("TITLE", notification.title)
                putExtra("DESCRIPTION", notification.description)
                putExtra("tName",tName)
            }
        intent.action=NotificationReceiver.ACTION_NOTIFICATION
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            LocalDateTime.now()
                .plusSeconds((hr*60*60)+(min*60))
                .atZone(ZoneId.systemDefault())
                .toEpochSecond()*1000,
            PendingIntent.getBroadcast(
                context,
                Random.nextInt(),
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }


    override fun setSundayEndAlarm() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.action = NotificationReceiver.ACTION_SUNDAY_TASK
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 23) // 23:00 hours
        calendar.set(Calendar.MINUTE, 59) // 59 minutes
        calendar.set(Calendar.SECOND, 59) // 59 seconds

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

}