package model

import io.ktor.client.plugins.*
import io.ktor.util.network.*

class NewsResponseCode {
    fun checkError(e: Throwable): Int {
        // Handle Error
        return when (e) {
            is RedirectResponseException -> {
                (e.response.status.value)
            }
            is ClientRequestException -> {
                (e.response.status.value)
            }
            is ServerResponseException -> {
                (e.response.status.value)
            }
            is UnresolvedAddressException -> {
                -1
            }
            else -> {
                -2
            }
        }
    }
}
sealed class NewsResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : NewsResponse<T>()
    data class Error(val code: Int) : NewsResponse<Nothing>()
}