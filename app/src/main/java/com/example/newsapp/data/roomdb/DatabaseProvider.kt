package com.example.newsapp.data.roomdb

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var instance: AppDatabase? = null
    fun getDb(context: Context): AppDatabase{
        if (instance==null){
            instance= Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "news_table"
            ).build()
        }
        return instance!!
    }
}