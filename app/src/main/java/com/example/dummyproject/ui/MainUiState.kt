package com.example.dummyproject.ui

sealed class MainUiState

object LoadingState : MainUiState()
object ContentState : MainUiState()
class ErrorState(val message: String) : MainUiState()