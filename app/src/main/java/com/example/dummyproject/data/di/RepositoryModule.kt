package com.example.dummyproject.data.di

import android.app.Application
import com.example.dummyproject.data.repository.Repository
import com.example.dummyproject.data.repository.RepositoryImpl
import com.example.dummyproject.network.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    @Provides
    fun provideImagineRepository(apiService: NetworkApi): Repository {
        return RepositoryImpl(apiService)
    }
}
