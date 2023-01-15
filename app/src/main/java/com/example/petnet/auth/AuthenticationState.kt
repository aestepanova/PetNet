package com.example.petnet.auth

import androidx.annotation.StringRes
import com.example.petnet.R


data class AuthenticationState (
    val authenticationMode: AuthenticationMode =
        AuthenticationMode.SIGN_IN,
    val email: String = "",
    val password: String = "",
    val login: String = "",
    val passwordRequirements: List<PasswordRequirements> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    var isauthenticate: Boolean = true
){
    fun isFormValid(): Boolean {
        if (authenticationMode == AuthenticationMode.SIGN_IN) {
            return password.isNotEmpty() == true &&
                    login.isNotEmpty() == true
        }
        else{
            return login.isNotEmpty() && password.isNotEmpty() &&
                    email.isNotEmpty() && passwordRequirements.size == 3 && email.contains("@") && email.contains(".")
        }
    }


}
    fun checkAuth(){

    }
enum class AuthenticationMode {
    SIGN_UP, SIGN_IN
}
enum class PasswordRequirements(
    @StringRes val label: Int
) {
    CAPITAL_LETTER(R.string.password_requirement_capital),
    NUMBER(R.string.password_requirement_digit),
    EIGHT_CHARACTERS(R.string.password_requirement_characters)
}