package com.example.goalstracker.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao){
    val allTaskByAsc = taskDao.getAllTaskByAscOrder()
    private val coroutineScope= CoroutineScope(Dispatchers.Main)

    fun addTask(task: Task){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.addTask(task)
        }
    }

    fun deleteTask(taskPosition:Int){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(taskPosition)
        }
    }
    fun deleteAllTask(){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteAllTask()
        }
    }
    fun pinTask(tName:String){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.pinTask(tName)
        }
    }
    fun taskCompleted(tName:String){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.taskCompleted(tName)
        }
    }
    fun taskNotCompleted(tName:String){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.taskNotCompleted(tName)
        }
    }

}