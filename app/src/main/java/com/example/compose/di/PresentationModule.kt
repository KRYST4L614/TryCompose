package com.example.compose.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.presentation.AddStudentViewModel
import com.example.compose.presentation.MainViewModel
import com.example.compose.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindItemListViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddStudentViewModel::class)
    fun bindAddItemViewModel(viewModel: AddStudentViewModel): ViewModel

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}