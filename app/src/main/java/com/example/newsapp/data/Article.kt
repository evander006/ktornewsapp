package com.example.newsapp.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "news_table")
data class Article(
    @ColumnInfo(name = "author") val author: String?=null,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
    @ColumnInfo(name = "source") val source: Source,
    @ColumnInfo(name = "title") val title: String,
    @PrimaryKey val url: String,
    @ColumnInfo(name = "urlToImage") val urlToImage: String? = null
)