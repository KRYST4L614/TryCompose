package com.example.compose.di

import com.example.compose.data.ItemsRepositoryImpl
import com.example.compose.data.ItemsSource
import com.example.compose.data.ItemsSourceImpl
import com.example.compose.domain.ItemsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Singleton
    @Binds
    fun bindItemsRepositoryImpl(impl: ItemsRepositoryImpl): ItemsRepository

    @Binds
    fun bindItemsSourceImpl(impl: ItemsSourceImpl): ItemsSource
}