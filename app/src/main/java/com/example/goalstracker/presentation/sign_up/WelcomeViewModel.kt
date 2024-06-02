package com.example.goalstracker.presentation.sign_up

import androidx.lifecycle.ViewModel
import com.example.goalstracker.data.Weekly
import com.example.goalstracker.data.WeeklyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val weeklyRepository: WeeklyRepository
) : ViewModel() {

    fun addWeekly(weekly: Weekly) {
        weeklyRepository.addWeekly(weekly)
    }

}