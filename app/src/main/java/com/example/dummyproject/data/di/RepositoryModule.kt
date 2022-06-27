package com.example.dummyproject.data.di

import com.example.dummyproject.domain.repository.Repository
import com.example.dummyproject.domain.repository.RepositoryImpl
import com.example.dummyproject.data.remote.network.NetworkApi
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
