package com.example.goalstracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    fun addTask(task: Task)

    @Query(
        "SELECT * FROM task_list ORDER BY CASE WHEN PinnedTask = 1 THEN 0 ELSE 1 END ASC, PinnedTask ASC, CASE WHEN TaskCompletion = 1 THEN 1 ELSE 0 END ASC"
    )
    fun getAllTaskByAscOrder(): Flow<List<Task>>

    @Query("DELETE FROM task_list")
    fun deleteAllTask()

    @Query("DELETE FROM task_list WHERE TaskPosition=:taskPosition")
    fun deleteTask(taskPosition: Int)

    @Query("UPDATE task_list SET PinnedTask=1 WHERE TaskPosition=:pos")
    fun pinTask(pos: Int)

    @Query("UPDATE task_list SET PinnedTask=0 WHERE TaskPosition=:pos")
    fun unPinTask(pos: Int)

    @Query("UPDATE task_list SET Alarm=0 WHERE TaskName=:tName")
    fun offAlarm(tName:String)

    @Query("UPDATE task_list SET TaskCompletion = 1 WHERE TaskPosition=:pos")
    fun taskCompleted(pos: Int)

    @Query("UPDATE task_list SET TaskCompletion = 0 WHERE TaskPosition=:pos")
    fun taskNotCompleted(pos: Int)

}
