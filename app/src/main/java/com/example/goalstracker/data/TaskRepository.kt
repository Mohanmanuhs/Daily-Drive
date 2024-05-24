package com.example.goalstracker.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao) {
    val allTaskByAsc = taskDao.getAllTaskByAscOrder()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.addTask(task)
        }
    }
    fun deleteTask(taskPosition: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(taskPosition)
        }
    }

    fun deleteAllTask() {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteAllTask()
        }
    }

    fun pinTask(pos: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.pinTask(pos)
        }
    }

    fun unPinTask(pos: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.unPinTask(pos)
        }
    }

    fun offAlarm(tName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.offAlarm(tName)
        }
    }

    fun taskCompleted(pos: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.taskCompleted(pos)
        }
    }

    fun taskNotCompleted(pos: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.taskNotCompleted(pos)
        }
    }

}