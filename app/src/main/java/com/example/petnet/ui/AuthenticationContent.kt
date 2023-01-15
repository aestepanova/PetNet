package com.example.petnet.ui



import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.petnet.auth.AuthenticationEvent
import com.example.petnet.auth.AuthenticationState


@ExperimentalAnimationApi
@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier
        .fillMaxHeight()
        .fillMaxHeight(),
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit,
    hideKeyboard: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(32.dp)
            .background(color = Color(0xFFFFFFFF)),
        contentAlignment = Alignment.TopCenter

    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            AuthenticationTitle(authenticationMode = authenticationState.authenticationMode)
        }
            AuthenticationForm(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                email = authenticationState.email ?: "",
                password = authenticationState.password?: "",
                login = authenticationState.login?: "",
                authenticationMode = authenticationState.authenticationMode,
                onEmailChanged = {
                    handleEvent(AuthenticationEvent.EmailChanged(it))
                },
                onPasswordChanged = {
                    handleEvent(AuthenticationEvent.PasswordChanged(it))
                },
                onAuthenticate = {
                    handleEvent(AuthenticationEvent.Authenticate)
                },
                enableAuthentication = authenticationState.isFormValid(),
                onToggleMode = {
                    handleEvent(
                        AuthenticationEvent.ToggleAuthenticationMode
                    )
                },
                onLoginChanged = {
                    handleEvent(AuthenticationEvent.LoginChanged(it))
                },
                hideKeyBoard = hideKeyboard,
                completedPasswordRequirements = authenticationState.passwordRequirements
            )

    }
}

