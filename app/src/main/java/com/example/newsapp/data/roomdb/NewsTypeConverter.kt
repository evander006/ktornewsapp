package com.example.newsapp.data.roomdb

import androidx.room.TypeConverter
import com.example.newsapp.data.Source
import kotlinx.serialization.json.Json

class NewsTypeConverter {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromSource(source: Source): String {
        return json.encodeToString(source)
    }

    @TypeConverter
    fun toSource(sourceString: String): Source {
        return json.decodeFromString(sourceString)
    }
}