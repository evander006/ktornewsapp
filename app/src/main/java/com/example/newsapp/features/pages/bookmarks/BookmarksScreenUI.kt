package com.example.newsapp.features.pages.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.newsapp.data.Article
import com.example.newsapp.features.effects.ShimmerNewsCardPlaceholder
import com.example.newsapp.features.pages.home.cards.NewsCard

@Composable
fun BookmarksScreenUI(
    innerPadding: PaddingValues,
    goToDetailScreen:(Article) -> Unit,
    viewmodel: BookmarksViewmodel
){
    val screenHeightDp= LocalConfiguration.current.screenHeightDp
    val estimatedCardHeight=120
    val shimmerItemCount = (screenHeightDp / estimatedCardHeight).toInt()+1

    val state by viewmodel.state.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = innerPadding) {
        items(state.articles){art->
            NewsCard(art){
                goToDetailScreen(art)
            }
        }
    }
}