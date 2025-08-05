package com.example.newsapp.features.pages.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.newsapp.data.Article

data class SearchState(
    val isSearching: Boolean=false,
    val searchQuery: String ="",
    val error: Boolean=false,
    val articles: List<Article> = emptyList()
)