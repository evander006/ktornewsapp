package com.example.newsapp.features.pages.search

import com.example.newsapp.data.Article

sealed interface SearchIntents {
    data class newsClick(val article: Article): SearchIntents
    data class searchQueryInput(val queryInput: String): SearchIntents
    data class searchingNews(val query: String): SearchIntents
}