package com.example.goalstracker.di

import android.content.Context
import androidx.room.Room
import com.example.goalstracker.data.TaskDao
import com.example.goalstracker.data.TaskRoomDatabase
import com.example.goalstracker.data.WeeklyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideTaskDao(appDb:TaskRoomDatabase):TaskDao{
        return appDb.taskDao()
    }

    @Provides
    fun provideWeeklyDao(appDb:TaskRoomDatabase):WeeklyDao{
        return appDb.weeklyDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): TaskRoomDatabase {
         return Room.databaseBuilder(
            context.applicationContext,
            TaskRoomDatabase::class.java,
            "task_db"
        ).build()
    }

}