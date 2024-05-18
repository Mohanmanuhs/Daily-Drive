package com.example.goalstracker.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
}