package com.example.goalstracker.presentation.add_task

import androidx.lifecycle.ViewModel
import com.example.goalstracker.data.Task
import com.example.goalstracker.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel()  {
    fun addTask(task:Task){
        taskRepository.addTask(task)
    }
}