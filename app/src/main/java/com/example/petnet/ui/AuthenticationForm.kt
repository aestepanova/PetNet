package com.example.petnet.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petnet.R
import com.example.petnet.auth.AuthenticationMode
import com.example.petnet.auth.PasswordRequirements
import com.example.petnet.ui.theme.Montserrat


@ExperimentalAnimationApi
@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String,
    password: String,
    login: String,
    enableAuthentication: Boolean,
    onEmailChanged: (email: String) -> Unit,
    onLoginChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onToggleMode: () -> Unit,
    onAuthenticate: () -> Unit,
    hideKeyBoard: () -> Unit,
    completedPasswordRequirements: List<PasswordRequirements>
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val passwordFocusRequester = FocusRequester()
        if (authenticationMode == AuthenticationMode.SIGN_IN){
        Spacer(modifier = Modifier.height(8.dp))
            LoginInput(
                modifier = Modifier.fillMaxWidth(),
                login = login,
                onLoginChanged = onLoginChanged,
                onNextClicked = { passwordFocusRequester.requestFocus() }
            )
            Spacer(modifier = Modifier.height(32.dp))
            PasswordInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                password = password,
                onPasswordChanged = onPasswordChanged,
                onDoneClicked = {
                    if (enableAuthentication == true){
                    run(onAuthenticate)
                }
                    else{
                                    run(hideKeyBoard)
                                }},
                onSubmitForm = onAuthenticate
        )
            Spacer(modifier = Modifier.height(32.dp))

            AnimatedVisibility(
                visible = authenticationMode ==
                        AuthenticationMode.SIGN_UP
            ){}
            Spacer(modifier = Modifier.height(40.dp))
            AuthenticationButton(
                enableAuthentication = enableAuthentication,
                authenticationMode = authenticationMode,
                onAuthenticate = onAuthenticate,
            )

            Spacer(modifier = Modifier.height(40.dp))

            ToggleAuthenticationMode(
                modifier = Modifier.fillMaxWidth(),
                authenticationMode = authenticationMode,
                toggleAuthentication = {
                    onToggleMode()
                }
            )
        }
        else {
            val emailFocusRequester = FocusRequester()
            Spacer(modifier = Modifier.height(8.dp))
            LoginInput(
                modifier = Modifier.fillMaxWidth(),
                login = login,
                onLoginChanged = onLoginChanged,
                onNextClicked = { emailFocusRequester.requestFocus() }
            )
            Spacer(modifier = Modifier.height(32.dp))
            EmailInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailFocusRequester),
                email = email,
                onEmailChanged = onEmailChanged,
                onNextClicked = { passwordFocusRequester.requestFocus() }
            )
            Spacer(modifier = Modifier.height(32.dp))
            PasswordInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                password = password,
                onPasswordChanged = onPasswordChanged,
                onSubmitForm = onAuthenticate,
                onDoneClicked = {if (enableAuthentication == true){
                    run(onAuthenticate)
                }
                else{
                    run(hideKeyBoard)
                }}
            )
            PasswordRequirements(satisfiedRequirements = completedPasswordRequirements )
            AnimatedVisibility(
                visible = authenticationMode ==
                        AuthenticationMode.SIGN_UP
            ){}
            Spacer(modifier = Modifier.height(40.dp))

            AuthenticationButton(
                enableAuthentication = enableAuthentication,
                authenticationMode = authenticationMode,
                onAuthenticate = onAuthenticate,
                )

            Spacer(modifier = Modifier.height(40.dp))

            ToggleAuthenticationMode(
                modifier = Modifier.fillMaxWidth(),
                authenticationMode = authenticationMode,
                toggleAuthentication = {
                    onToggleMode()
                }
            )
        }
        }

    }

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onAuthenticate()
        },
        enabled = enableAuthentication,
        colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color(0xFF8EACB9), backgroundColor = Color(0xFF03A9F4), contentColor = Color(0xFFFFFFFF)),
    ) {
        Text(
            text = stringResource(
                if (authenticationMode ==
                    AuthenticationMode.SIGN_IN
                ) {
                    R.string.action_sign_in
                } else {
                    R.string.action_sign_up
                }
            ),
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = Montserrat,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthentication: () -> Unit
) {
        TextButton(
            modifier = Modifier,
            onClick = {
                toggleAuthentication()
            }
        ) {
            Text(
                text = stringResource(
                    if (authenticationMode ==
                        AuthenticationMode.SIGN_IN
                    ) {
                        R.string.action_need_account
                    } else {
                        R.string.action_already_have_account
                    }
                ),
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = Color(0xFF03A9F4),
                fontFamily = Montserrat,
                modifier = Modifier.padding(8.dp)
            )
        }
}