package model

import com.google.gson.annotations.SerializedName

class LoginRequestDto(@SerializedName("login") val login: String, @SerializedName("password") val password: String)