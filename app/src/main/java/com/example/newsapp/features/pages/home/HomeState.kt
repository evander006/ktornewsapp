package com.example.newsapp.features.pages.home

import com.example.newsapp.data.Article

data class HomeState(
    val isLoading: Boolean=true,
    val error: Boolean=false,
    val articles: List<Article> = emptyList()
)