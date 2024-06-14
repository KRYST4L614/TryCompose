package com.example.compose.presentation

import androidx.lifecycle.ViewModel
import com.example.compose.domain.entities.ListItemData
import com.example.compose.domain.usecases.GetItemsUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    getItemsUseCase: GetItemsUseCase
) : ViewModel() {
    val data: ListItemData = getItemsUseCase()
}