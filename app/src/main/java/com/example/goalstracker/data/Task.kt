package com.example.goalstracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_list")
class Task{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TaskPosition")
    var pos:Int=0

    @ColumnInfo(name = "PinnedTask")
    var pTask:Boolean=false

    @ColumnInfo(name = "TaskName")
    var tName: String = ""

    @ColumnInfo(name = "TaskCompletion")
    var tc:Boolean=false

    @ColumnInfo(name = "Alarm")
    var a:Boolean=false

    @ColumnInfo(name = "AlarmHour")
    var aHour:Int=0

    @ColumnInfo(name = "AlarmMinute")
    var aMinute:Int=0

    constructor()
    constructor(tName:String){
        this.tName=tName
    }
    constructor(tName: String,a:Boolean,aHour:Int,aMinute:Int){
        this.tName=tName
        this.a=a
        this.aHour=aHour
        this.aMinute=aMinute
    }
}