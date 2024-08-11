package com.example.compose.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.navigation.ItemsList
import com.example.compose.presentation.AddStudentState
import com.example.compose.presentation.AddStudentState.Content
import com.example.compose.presentation.AddStudentState.Loading
import com.example.compose.presentation.AddStudentViewModel

@Composable
fun AddStudentScreen(
    modifier: Modifier = Modifier,
    viewModel: AddStudentViewModel = viewModel(),
    navController: NavController
) {
    AddStudentScreen(
        modifier = modifier,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        backToList = navController::navigate,
        checkName = viewModel::checkName,
        checkSecondName = viewModel::checkSecondName,
        handleButtonClick = viewModel::handleButtonClick
    )
}

@Composable
fun AddStudentScreen(
    modifier: Modifier,
    state: AddStudentState,
    backToList: (ItemsList) -> Unit,
    checkName: (String) -> Unit,
    checkSecondName: (String) -> Unit,
    handleButtonClick: (String, String, String, Boolean) -> Unit
) {

    var name by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var hasPortfolio by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()


    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val animateFadeIn by animateFloatAsState(
            targetValue = if (state is Loading) 1F else 0F,
            animationSpec = tween(durationMillis = 1000),
            label = "animateFadeIn"
        ) { _ ->
            backToList(ItemsList)
        }
        CircularProgressIndicator(Modifier.alpha(animateFadeIn))


        val internalModifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .alpha(1 + animateFadeIn * -1)

        TextField(
            modifier = internalModifier,
            text = name,
            isError = if (state is Content) state.nameError else false,
            supportingText = { Text(stringResource(id = R.string.name_error)) },
            label = { Text(text = stringResource(id = R.string.name)) }
        ) {
            name = it
            checkName(name)
        }

        TextField(
            modifier = internalModifier,
            text = secondName,
            isError = if (state is Content) state.secondNameError else false,
            supportingText = { Text(stringResource(id = R.string.name_error)) },
            label = { Text(text = stringResource(id = R.string.second_name)) }
        ) {
            secondName = it
            checkSecondName(secondName)
        }

        TextField(
            modifier = internalModifier,
            text = description,
            label = { Text(text = stringResource(id = R.string.description)) }
        ) {
            description = it
        }

        HasPortfolioCheckBox(modifier = internalModifier, hasPortfolio = hasPortfolio) {
            hasPortfolio = it
        }

        SaveButton(
            modifier = internalModifier,
            enabled = state !is Loading
        ) {
            handleButtonClick(name, secondName, description, hasPortfolio)
        }
    }
}

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    text: String,
    isError: Boolean? = null,
    supportingText: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        label = label,
        isError = isError ?: false,
        supportingText = if (isError == true) supportingText else null,
        trailingIcon = {
            if (isError == true)
                Icon(
                    Icons.Filled.Info,
                    "error",
                    tint = MaterialTheme.colorScheme.error
                )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HasPortfolioCheckBox(
    modifier: Modifier = Modifier,
    hasPortfolio: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalMinimumInteractiveComponentEnforcement provides false
        ) {
            Checkbox(
                modifier = Modifier.padding(end = 8.dp),
                checked = hasPortfolio,
                onCheckedChange = onValueChange
            )
        }
        Text(text = stringResource(id = R.string.has_portfolio))
    }
}

@Composable
fun SaveButton(modifier: Modifier = Modifier, enabled: Boolean, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        Text(text = stringResource(id = R.string.save))
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun AddStudentScreenPreview() {
    AddStudentScreen(
        modifier = Modifier,
        state = Content(false, false),
        backToList = { _ -> },
        checkName = { _ -> },
        checkSecondName = { _ -> },
        handleButtonClick = { _, _, _, _ -> }
    )
}