package com.example.goalstracker.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Task::class,Weekly::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun weeklyDao():WeeklyDao
}