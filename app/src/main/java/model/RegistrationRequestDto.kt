package model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequestDto(@SerializedName("login") val login: String, @SerializedName("email") val email: String,@SerializedName("password") val password: String)