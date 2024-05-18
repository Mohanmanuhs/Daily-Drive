package com.example.goalstracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    fun addTask(task: Task)

    @Query("SELECT * FROM task_list ORDER BY TaskPosition ASC")
    fun getAllTaskByAscOrder(): Flow<List<Task>>

    @Query("DELETE FROM task_list")
    fun deleteAllTask()

    @Query("DELETE FROM task_list WHERE TaskPosition=:taskPosition")
    fun deleteTask(taskPosition:Int)

    @Query("UPDATE task_list SET PinnedTask=1 WHERE TaskName=:taskName")
    fun pinTask(taskName:String)

    @Query("UPDATE task_list SET TaskCompletion = 1 WHERE TaskName=:taskName")
    fun taskCompleted(taskName: String)

    @Query("UPDATE task_list SET TaskCompletion = 0 WHERE TaskName=:taskName")
    fun taskNotCompleted(taskName: String)

    @Query("SELECT * FROM task_list WHERE Alarm=1")
    fun getAlarmedTask():Flow<List<Task>>

}
