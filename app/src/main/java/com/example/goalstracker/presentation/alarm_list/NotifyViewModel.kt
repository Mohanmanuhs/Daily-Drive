package com.example.goalstracker.presentation.alarm_list

import androidx.lifecycle.ViewModel
import com.example.goalstracker.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotifyViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel()  {
    val allTask = taskRepository.allTaskByAsc


}