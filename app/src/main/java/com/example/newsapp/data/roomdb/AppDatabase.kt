package com.example.newsapp.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.Article


@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}