package com.example.goalstracker.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.goalstracker.data.WeeklyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val weeklyRepository: WeeklyRepository) : ViewModel() {
    val weekly = weeklyRepository.weekly

    fun getWeeklyRepo() {
        weeklyRepository.getWeeklyRepo()
    }

}