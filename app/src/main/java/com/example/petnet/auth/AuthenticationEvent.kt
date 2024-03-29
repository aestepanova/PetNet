package com.example.petnet.auth

sealed class AuthenticationEvent {

    object ToggleAuthenticationMode: AuthenticationEvent()

    class EmailChanged(val emailAddress: String): AuthenticationEvent()

    class PasswordChanged(val password: String): AuthenticationEvent()

    class LoginChanged(val login: String): AuthenticationEvent()

    object Authenticate: AuthenticationEvent()

    object ErrorDismissed: AuthenticationEvent()
}
