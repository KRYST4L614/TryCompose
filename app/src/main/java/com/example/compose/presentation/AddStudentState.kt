package com.example.compose.presentation

import androidx.compose.runtime.Stable

@Stable
sealed interface AddStudentState {
    data class Content(
        val nameError: Boolean,
        val secondNameError: Boolean
    ) : AddStudentState

    data object Loading : AddStudentState
}