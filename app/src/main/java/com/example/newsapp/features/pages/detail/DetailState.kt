package com.example.newsapp.features.pages.detail

import com.example.newsapp.data.Article

data class DetailState(
    val error: Boolean=false,
    val articleInfo: Article? = null,
    val isFavourite: Boolean = false
)