package com.example.goalstracker

import com.example.goalstracker.data.Notification

interface NotificationScheduler {
    fun schedule(notification: Notification, hr:Long, min:Long)
}