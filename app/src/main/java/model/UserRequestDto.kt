package model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDto(@SerializedName("token") val token: String )