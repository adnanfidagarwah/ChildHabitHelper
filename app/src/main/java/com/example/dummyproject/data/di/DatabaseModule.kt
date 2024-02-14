package com.example.dummyproject.data.di

import android.content.Context
import androidx.room.Room
import com.example.dummyproject.data.local.database.AppDatabase
import com.example.dummyproject.data.local.database.RoomDbInitializer
import com.example.dummyproject.data.local.database.goal.GoalDao
import com.example.dummyproject.data.local.database.goal_category.GoalCategoryDao
import com.example.dummyproject.presentation.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        goalCategoryProvider: Provider<GoalCategoryDao>,
        goalProvider: Provider<GoalDao>
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).addCallback(
        RoomDbInitializer(goalCategoryProvider = goalCategoryProvider, goalProvider = goalProvider)
    ).build()

    @Singleton
    @Provides
    fun provideChildDao(database: AppDatabase) = database.childDao()

    @Singleton
    @Provides
    fun provideGoalCategoryDao(database: AppDatabase) = database.goalCategoryDao()

    @Singleton
    @Provides
    fun provideGoalDao(database: AppDatabase) = database.goalDao()
}