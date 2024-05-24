package com.example.goalstracker.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeeklyRepository(private val weeklyDao: WeeklyDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val weekly = MutableStateFlow(Weekly())

    fun getWeeklyRepo() {
        coroutineScope.launch(Dispatchers.IO) {
            weekly.value = weeklyDao.getWeekly()
        }
    }

    fun sundayClear() {
        coroutineScope.launch(Dispatchers.IO){
            weeklyDao.sundayClear(Weekly())
        }
    }

    fun updateMonday(monday: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.updateMonday(monday)
        }
    }

    fun updateTuesDay(tuesday: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.updateTuesDay(tuesday)
        }
    }

    fun updateWednesday(wednesday: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.updateWednesday(wednesday)
        }
    }

    fun updateThursday(thursday: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.updateThursday(thursday)
        }
    }

    fun updateFriday(friday: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.updateFriday(friday)
        }
    }

    fun updateSaturday(saturday: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.updateSaturday(saturday)
        }
    }

    fun updateSunday(sunday: Float) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.updateSunday(sunday)
        }
    }

    fun addWeekly(weekly: Weekly) {
        coroutineScope.launch(Dispatchers.IO) {
            weeklyDao.addWeekly(weekly)
        }
    }

}