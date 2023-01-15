package com.example.petnet.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.petnet.auth.AuthenticationViewModel


@ExperimentalAnimationApi
@Composable
fun AuthenticationScreen(hideKeyboard: () -> Unit, viewModel: AuthenticationViewModel) {

        MaterialTheme {
            AuthenticationContent(
                modifier = Modifier,
                authenticationState = viewModel.uiState.collectAsState().value,
                handleEvent = viewModel::handleEvent,
                hideKeyboard = hideKeyboard
            )
        }


}
