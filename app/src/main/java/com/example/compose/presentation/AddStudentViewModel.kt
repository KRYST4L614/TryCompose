package com.example.compose.presentation

import androidx.lifecycle.ViewModel
import com.example.compose.domain.entities.ListItem.StudentItem
import com.example.compose.domain.usecases.AddStudentUseCase
import com.example.compose.presentation.AddStudentState.Content
import com.example.compose.presentation.AddStudentState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AddStudentViewModel @Inject constructor(
    private val addItemsUseCase: AddStudentUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<AddStudentState>(
        Content(
            nameError = false,
            secondNameError = false
        )
    )
    val state: StateFlow<AddStudentState>
        get() = _state

    fun checkName(name: String) {
        _state.value = (_state.value as Content).copy(nameError = name.isBlank())
    }

    fun checkSecondName(secondName: String) {
        _state.value =
            (_state.value as Content).copy(secondNameError = secondName.isBlank())
    }

    fun handleButtonClick(
        name: String,
        secondName: String,
        description: String,
        hasPortfolio: Boolean
    ) {
        if (name.isNotBlank() && secondName.isNotBlank()) {
            _state.value = Loading
            addItem(StudentItem(name, secondName, description, hasPortfolio))
        } else {
            _state.value =
                Content(nameError = name.isBlank(), secondNameError = secondName.isBlank())
        }
    }

    private fun addItem(newStudent: StudentItem) {
        addItemsUseCase(newStudent)
    }
}