package com.example.goalstracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weekly")
data class Weekly(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "WeeklyPosition") var pos: Int=0,

    @ColumnInfo(name = "MONDAY") var monday: Float=0f,
    @ColumnInfo(name = "TUESDAY") var tuesday: Float=0f,
    @ColumnInfo(name = "WEDNESDAY") var wednesday: Float=0f,
    @ColumnInfo(name = "THURSDAY") var thursday: Float=0f,
    @ColumnInfo(name = "FRIDAY") var friday: Float=0f,
    @ColumnInfo(name = "SATURDAY") var saturday: Float=0f,
    @ColumnInfo(name = "SUNDAY") var sunday: Float=0f
)