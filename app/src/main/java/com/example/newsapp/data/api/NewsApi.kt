package com.example.newsapp.data.api

import com.example.newsapp.data.Article

interface NewsApi{
    suspend fun getNews(): List<Article>
    suspend fun searchNews(q: String): List<Article>

}