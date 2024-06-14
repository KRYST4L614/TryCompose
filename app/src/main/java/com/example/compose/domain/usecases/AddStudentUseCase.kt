package com.example.compose.domain.usecases

import com.example.compose.domain.ItemsRepository
import com.example.compose.domain.entities.ListItem.StudentItem
import javax.inject.Inject

class AddStudentUseCase @Inject constructor(
    private val repository: ItemsRepository
) {
    operator fun invoke(newStudent: StudentItem) = repository.addStudent(newStudent)
}