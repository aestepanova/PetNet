package model

import androidx.lifecycle.LiveData
import androidx.room.*
import model.Content

@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: Content): Long

    @Query("SELECT * FROM tb_content")
    fun getSavedContent(): LiveData<List<Content>>

    @Query("SELECT EXISTS (SELECT 1 FROM tb_content WHERE contentId = :id)")
    fun existsItem(id: Int): LiveData<Boolean>

    @Query("DELETE FROM tb_content WHERE contentId = :id")
    suspend fun deleteOneItem(id: Int)

    @Query("DELETE FROM tb_content")
    suspend fun deleteAllItem()
}