package com.example.newsapp.features.pages.bookmarks

import com.example.newsapp.data.Article

sealed interface BookmarksIntent {
    data class newsClicked(val article: Article): BookmarksIntent
}