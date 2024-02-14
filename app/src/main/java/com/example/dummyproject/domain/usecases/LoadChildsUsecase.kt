package com.example.dummyproject.domain.usecases

import com.example.dummyproject.domain.repository.Repository
import javax.inject.Inject

class LoadChildsUsecase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.loadChild()
}