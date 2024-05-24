package com.example.goalstracker.presentation.home

import androidx.lifecycle.ViewModel
import com.example.goalstracker.data.TaskRepository
import com.example.goalstracker.data.Weekly
import com.example.goalstracker.data.WeeklyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val weeklyRepository: WeeklyRepository
) : ViewModel() {
    val allTask = taskRepository.allTaskByAsc
    fun taskCompleted(taskPosition: Int) {
        taskRepository.taskCompleted(taskPosition)
    }
    fun updateWeekly(value:Float){
        val localDate = LocalDate.now().dayOfWeek
        val map = mapOf(
            "MONDAY" to { weeklyRepository.updateMonday(value) },
            "TUESDAY" to { weeklyRepository.updateTuesDay(value) },
            "WEDNESDAY" to { weeklyRepository.updateWednesday(value) },
            "THURSDAY" to { weeklyRepository.updateThursday(value) },
            "FRIDAY" to { weeklyRepository.updateFriday(value) },
            "SATURDAY" to { weeklyRepository.updateSaturday(value) },
            "SUNDAY" to { weeklyRepository.updateSunday(value) }
        )
        map[localDate.toString()]?.invoke()
    }
    
    fun deleteAllTask() {
        taskRepository.deleteAllTask()
    }

    fun addWeekly(weekly: Weekly) {
        weeklyRepository.addWeekly(weekly)
    }

    fun taskNotCompleted(taskPosition: Int) {
        taskRepository.taskNotCompleted(taskPosition)
    }

    fun deleteTask(taskPosition: Int) {
        taskRepository.deleteTask(taskPosition)
    }

    fun pinTask(taskPosition: Int) {
        taskRepository.pinTask(taskPosition)
    }

    fun unPinTask(taskPosition: Int) {
        taskRepository.unPinTask(taskPosition)
    }


}