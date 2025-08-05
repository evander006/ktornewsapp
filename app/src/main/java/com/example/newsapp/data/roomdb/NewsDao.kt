package com.example.newsapp.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getNews(): Flow<List<Article>>

    @Insert
    suspend fun insertToFav(article: Article)

    @Delete
    suspend fun deleteFromFav(article: Article)

    @Query("SELECT * FROM news_table WHERE url = :url LIMIT 1")
    suspend fun getArticleByUrl(url: String): Article?
}