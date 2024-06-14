package com.example.compose.presentation

sealed interface AddStudentState {
    data class Content(
        val nameError: Boolean,
        val secondNameError: Boolean
    ) : AddStudentState

    data object Loading : AddStudentState
}