package com.example.newsapp.features.pages.bookmarks

import com.example.newsapp.data.Article

data class BookmarksState(
    val error: Boolean =false,
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList()
)