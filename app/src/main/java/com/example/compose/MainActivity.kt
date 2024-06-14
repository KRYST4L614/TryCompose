package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.navigation.AddStudent
import com.example.compose.navigation.ItemsList
import com.example.compose.navigation.NavigationHost
import com.example.compose.presentation.ViewModelFactory
import com.example.compose.ui.theme.ComposeTheme
import javax.inject.Inject


class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        setContent {
            ComposeTheme {
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(id = R.string.shift_label)) },
                            actions = {
                                if (navBackStackEntry.value?.destination?.route
                                    == ItemsList::class.qualifiedName
                                )
                                    IconButton(onClick = {
                                        navController.navigate(AddStudent)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_menu_add),
                                            contentDescription = null,
                                            tint = MaterialTheme
                                                .colorScheme
                                                .onSurface
                                                .copy(0.54F)
                                        )
                                    }
                            })
                    },
                    content = { padding ->
                        NavigationHost(
                            navHostController = navController,
                            viewModelFactory = viewModelFactory,
                            padding = padding
                        )
                    }
                )
            }
        }
    }
}
