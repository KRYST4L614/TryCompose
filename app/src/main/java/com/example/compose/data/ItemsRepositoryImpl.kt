package com.example.compose.data

import com.example.compose.domain.ItemsRepository
import com.example.compose.domain.entities.ListItem.StudentItem
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val source: ItemsSource
) : ItemsRepository {
    override fun getItems() = source.getItems()

    override fun addStudent(newStudent: StudentItem) = source.addStudent(newStudent)
}