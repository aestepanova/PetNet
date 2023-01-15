package com.example.petnet.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.ContentDB
import model.Repository
import org.koin.android.ext.koin.androidContext
import kotlin.text.Typography.dagger

class AuthenticationViewModel(private val repository: Repository, private val prefs: SharedPreferences
) : ViewModel() {
    val uiState = MutableStateFlow(AuthenticationState())
    val auth = MutableStateFlow(uiState.value.isauthenticate)


    private fun toggleAuthenticationMode() {
        val authenticationMode = uiState.value.authenticationMode
        val newAuthenticationMode = if (
            authenticationMode == AuthenticationMode.SIGN_IN
        ) {
            AuthenticationMode.SIGN_UP
        } else {
            AuthenticationMode.SIGN_IN
        }
        uiState.value = uiState.value.copy(
            authenticationMode = newAuthenticationMode
        )
    }
    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            is AuthenticationEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }
            is AuthenticationEvent.EmailChanged -> {
                updateEmail(authenticationEvent.emailAddress)
            }
            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(authenticationEvent.password)
            }
            is AuthenticationEvent.LoginChanged -> {
                updateLogin(authenticationEvent.login)
            }
            is AuthenticationEvent.Authenticate -> {
                authenticate()
            }
        }
    }
    private fun updateEmail(email: String) {
        uiState.value = uiState.value.copy(
            email = email
        )
    }
    private fun updateLogin(login: String) {
        uiState.value = uiState.value.copy(
            login = login
        )
    }
    private fun updatePassword(password: String) {

        val requirements = mutableListOf<PasswordRequirements>()
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }
        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }
        uiState.value = uiState.value.copy(
            password = password,
            passwordRequirements = requirements.toList()
        )
    }
    private fun authenticate() {
        uiState.value = uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.Default){

            if (uiState.value.authenticationMode == AuthenticationMode.SIGN_UP) {
                repository.registerUser(
                    uiState.value.login!!,
                    uiState.value.email!!,
                    uiState.value.password!!
                )
            }
            else {
                val token = repository.loginUser(uiState.value.login!!,uiState.value.password!!)?: ""
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putString("Token", token).apply()
                uiState.value = uiState.value.copy(
                    isauthenticate = repository.checkToken(token)
                )


            }
            //delay(5000L)
            withContext(Dispatchers.Main) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = "Something went wrong!"
                )
            }
        }
    }
    fun checkToken(token: String){
        viewModelScope.launch(Dispatchers.Main) {
                uiState.value = uiState.value.copy(
                    isauthenticate = repository.checkToken(token)
                )
        }
    }

}