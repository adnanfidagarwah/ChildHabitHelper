package com.example.dummyproject.data.usecases

import com.example.dummyproject.data.repository.Repository
import javax.inject.Inject

class LoadRepositoriesUsecase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.loadRepositories()
}