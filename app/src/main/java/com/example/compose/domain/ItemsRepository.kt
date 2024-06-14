package com.example.compose.domain

import com.example.compose.domain.entities.ListItem.StudentItem
import com.example.compose.domain.entities.ListItemData

interface ItemsRepository {
    fun getItems(): ListItemData

    fun addStudent(newStudent: StudentItem)
}