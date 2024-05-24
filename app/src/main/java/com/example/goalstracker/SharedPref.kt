package com.example.goalstracker

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("myPref", Context.MODE_PRIVATE)

    val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun getTotal(): Int {
        return sharedPreferences.getInt("total",0)
    }
    fun getCompleted(): Int {
        return sharedPreferences.getInt("completed",0)
    }

    fun getTodayBoolean(todayDateString:String):Boolean {
        return sharedPreferences.getBoolean(todayDateString,false)
    }

    fun putTodayBoolean(todayDateString:String){
        editor.putBoolean(todayDateString,true).apply()
    }


    fun isFirstTime() {
        editor.putBoolean("isFirstTime",false)
        editor.putInt("total",0)
        editor.putInt("completed",0)
        editor.apply()
    }
    fun incrementTotal() {
        editor.putInt("total", sharedPreferences.getInt("total", 0) + 1)
        editor.apply()
    }

    fun decrementTotal() {
        editor.putInt("total", sharedPreferences.getInt("total", 0) - 1)
        editor.apply()
    }

    fun incrementCompleted() {
        editor.putInt("completed", sharedPreferences.getInt("completed", 0) + 1)
        editor.apply()
    }

    fun decrementCompleted() {
        editor.putInt("completed", sharedPreferences.getInt("completed", 0) - 1)
        editor.apply()
    }
    fun deleteAll() {
        editor.putInt("completed",0)
        editor.putInt("total",0)
        editor.apply()
    }

}