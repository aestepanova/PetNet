package model


import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*


class NewsProvider(val prefs: SharedPreferences) {

    val BASE_URL = "http://192.168.1.66:8080"
    private val news_response_code = NewsResponseCode()
    val my_id = 9
    private val token = "Bearer " + getToken(prefs)

    fun getToken(prefs: SharedPreferences): String{
        return prefs.getString("Token", "")!!

    }
    private val client = HttpClient(CIO) {
        install(DefaultRequest) {
            header("Accept", "application/json")
            header("Content-type", "application/json")
            contentType(ContentType.Application.Json)
        }

        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        //Now you see response logs inside terminal
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }

        //Print other logs
        install(ResponseObserver) {
            onResponse { response ->
                Log.d("ApiService", "HTTP status: ${response.status.value}")
            }
        }

    }

    fun getLogin(): String{
        return prefs.getString("Login", "")!!
    }
    fun getEmail(): String{
        return prefs.getString("Email", "")!!
    }


    fun logOut(){
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("Token", "").apply()
    }
    suspend fun registerUser(login: String, email: String, password: String) {
        val serializer: GsonSerializer = GsonSerializer()
        val request: HttpResponse =
                this.client.post(this.BASE_URL + "/register") {
                    val gson = Gson()
                    setBody(gson.toJson(RegistrationRequestDto(login, email, password)))
                    contentType(ContentType.Application.Json)
                }

    }
    suspend fun loginUser(login: String, password: String): String? {
        return try {
            val response: HttpResponse =
                client.post(this.BASE_URL + "/login") {
                    val gson = Gson()
                    contentType(ContentType.Application.Json)
                    setBody(gson.toJson(LoginRequestDto(login, password)))
                }
            val st = response.status.toString()
            if (st == "200 "){
                val token = response.bodyAsText()
                return token
            }
            else{
                return null
            }
        }catch (e: Throwable) {
            (NewsResponse.Error(news_response_code.checkError(e))).toString()
        }
    }


    suspend fun checkToken(token: String): Boolean {
        try{
            val serializer: GsonSerializer = GsonSerializer()
            val request: HttpResponse =
                this.client.get(this.BASE_URL + "/users/by_token") {
                    val gson = Gson()
                    setBody(prefs.getString("Token", "")!!)
                    header("Authorization", "Bearer " + getToken(prefs))
                }
            val st = request.status.toString()
            return st == "200 "
        } catch (e: Throwable) {
            return false
        }
    }

}