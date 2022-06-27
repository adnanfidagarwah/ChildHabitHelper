package com.example.dummyproject.presentation.ui

sealed class MainUiState

object LoadingState : MainUiState()
object ContentState : MainUiState()
class ErrorState(val message: String) : MainUiState()