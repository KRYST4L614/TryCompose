package com.example.compose.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.ui.screens.AddStudentScreen
import com.example.compose.ui.screens.ItemListScreen
import kotlinx.serialization.Serializable

@Serializable
object ItemsList

@Serializable
object AddStudent


@Composable
fun NavigationHost(
    navHostController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    padding: PaddingValues
) {
    NavHost(navController = navHostController, startDestination = ItemsList) {
        composable<AddStudent>(
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 250)
                )
            },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 250)
                )
            },
            popEnterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 250)
                )
            },
            popExitTransition = {
                slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 250)
                )
            }
        ) {
            AddStudentScreen(
                Modifier.fillMaxSize(),
                viewModel = viewModel(factory = viewModelFactory),
                navHostController
            )
        }
        composable<ItemsList>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(durationMillis = 100)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(durationMillis = 100)
                )
            }
        ) {
            ItemListScreen(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                viewModel = viewModel(factory = viewModelFactory)
            )
        }
    }
}