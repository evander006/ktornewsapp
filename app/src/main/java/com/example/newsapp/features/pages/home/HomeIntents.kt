package com.example.newsapp.features.pages.home

import com.example.newsapp.data.Article

sealed interface HomeIntents {
    data object isFailedLoad: HomeIntents
    data object LoadNews : HomeIntents
}