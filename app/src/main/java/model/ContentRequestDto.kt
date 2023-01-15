package model
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ContentRequestDto(@SerializedName("tags") val tags: List<Int>)
