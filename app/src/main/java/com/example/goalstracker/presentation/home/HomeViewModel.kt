package com.example.goalstracker.presentation.home

import androidx.lifecycle.ViewModel
import com.example.goalstracker.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {
    val allTask = taskRepository.allTaskByAsc
    fun taskCompleted(tName:String){
        taskRepository.taskCompleted(tName)
    }

    fun taskNotCompleted(tName:String){
        taskRepository.taskNotCompleted(tName)
    }

    fun deleteTask(taskPosition:Int){
        taskRepository.deleteTask(taskPosition)
    }



}