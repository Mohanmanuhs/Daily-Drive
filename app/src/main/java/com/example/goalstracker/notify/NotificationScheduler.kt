package com.example.goalstracker.notify

import com.example.goalstracker.data.Notification

interface NotificationScheduler {
    fun schedule(notification: Notification,tName:String, hr:Long, min:Long)
    fun setSundayEndAlarm()
}