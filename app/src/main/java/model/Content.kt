package model

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Parcelable
import android.text.style.TtsSpan
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
import dagger.internal.SetBuilder
import kotlinx.android.parcel.Parcelize
import org.ocpsoft.prettytime.PrettyTime
import java.io.Serializable
import java.sql.Timestamp
import java.util.*
import java.util.stream.Collectors
@Parcelize
@Entity(tableName = "tb_content", indices = [Index(value = ["url"], unique = true)])
data class Content(
    @PrimaryKey(autoGenerate = true)
    var contentId: Int = 0,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("date")
    val date: Date? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("tags")
    val tags: List<String> = mutableListOf()
) : Parcelable
    class Converters {
        @TypeConverter
        fun fromTag(value: List<String>?): String? {
            return value?.let { it.joinToString(separator = ",") }
        }

        @TypeConverter
        fun toTag(value: String?): List<String>? {

            return value?.let { value.split(",") }
        }

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.N)
        fun fromDate(date: Date?): Long? {
            return  date?.let { date.getTime() }
        }

        @RequiresApi(Build.VERSION_CODES.N)
        @TypeConverter
        fun toDate(date: Long?): Date? {
            val newDate = date?.let { Date(date) }
            return newDate
        }
    }
