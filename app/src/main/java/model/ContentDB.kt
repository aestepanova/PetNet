package model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Content::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ContentDB : RoomDatabase() {

    abstract fun getDao(): ContentDao

}