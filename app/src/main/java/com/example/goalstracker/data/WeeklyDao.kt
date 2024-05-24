package com.example.goalstracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeeklyDao {

    @Insert
    fun addWeekly(weekly: Weekly)

    @Query("SELECT * FROM weekly WHERE WeeklyPosition=0")
    fun getWeekly():Weekly

    @Query("UPDATE weekly SET MONDAY=:monday WHERE WeeklyPosition=0")
    fun updateMonday(monday:Float)

    @Query("UPDATE weekly SET TUESDAY=:tuesday WHERE WeeklyPosition=0")
    fun updateTuesDay(tuesday:Float)

    @Query("UPDATE weekly SET WEDNESDAY=:wednesday WHERE WeeklyPosition=0")
    fun updateWednesday(wednesday:Float)

    @Query("UPDATE weekly SET THURSDAY=:thursday WHERE WeeklyPosition=0")
    fun updateThursday(thursday:Float)

    @Query("UPDATE weekly SET FRIDAY=:friday WHERE WeeklyPosition=0")
    fun updateFriday(friday:Float)

    @Query("UPDATE weekly SET SATURDAY=:saturday WHERE WeeklyPosition=0")
    fun updateSaturday(saturday:Float)

    @Query("UPDATE weekly SET SUNDAY=:sunday WHERE WeeklyPosition=0")
    fun updateSunday(sunday:Float)

    @Update
    fun sundayClear(weekly: Weekly)

}
