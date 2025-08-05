package com.example.newsapp.features.pages.detail

import com.example.newsapp.data.Article

sealed interface DetailIntent {
    data class addToFavourite(val article: Article): DetailIntent
    data class removeFromFavourite(val article: Article): DetailIntent
    data class CheckIfBookmarked(val article: Article): DetailIntent

}