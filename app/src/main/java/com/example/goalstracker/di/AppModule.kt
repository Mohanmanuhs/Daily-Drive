package com.example.goalstracker.di

import android.content.Context
import com.example.goalstracker.AppNotificationManager
import com.example.goalstracker.data.TaskDao
import com.example.goalstracker.data.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao):TaskRepository {
        return TaskRepository(taskDao)
    }


    @Provides
    @Singleton
    fun provideAppNotificationManager(
        @ApplicationContext context: Context
    ): AppNotificationManager =
        AppNotificationManager(context)
}