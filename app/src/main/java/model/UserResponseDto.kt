package model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class UserResponseDto(@SerializedName("id") val id: Int,@SerializedName("email")  val email: String,@SerializedName("password")  val password: String,@SerializedName("login")  val login: String)