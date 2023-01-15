package model


import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
class ContentResponseDto(val id: Int, val type: Int, val date: Timestamp?, val text: String, val title: String, val author: String){
    fun str(): List<String>{
        val res = mutableListOf<String>()
        return res as List<String>
    }
}

@Serializable
class ContentDto(val id: Int, val type: Int, val text: String, val title: String, val author: String, val tags: List<TagDto>){
    fun str(): List<String>{
        val res = mutableListOf<String>()
        this.tags.forEach(){
            res.add(it.name)
        }
        return res as List<String>
    }
}
@Serializable
class TagDto(val id: Int?, val name: String)