package com.example.compose.domain.usecases

import com.example.compose.domain.ItemsRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemsRepository
) {
    operator fun invoke() = repository.getItems()
}