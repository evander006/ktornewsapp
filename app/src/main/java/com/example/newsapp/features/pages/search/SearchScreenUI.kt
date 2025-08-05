package com.example.newsapp.features.pages.search

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.newsapp.features.effects.Searchbar
import com.example.newsapp.features.effects.ShimmerNewsCardPlaceholder

import com.example.newsapp.features.pages.home.cards.NewsCard


@Composable
fun SearchScreenUI(
    innerPadding: PaddingValues,
    viewModel: SearchViewModel,
) {
    val contex= LocalContext.current
    val screenHeightDp= LocalConfiguration.current.screenHeightDp
    val estimatedCardHeight=120
    val shimmerItemCount = (screenHeightDp / estimatedCardHeight).toInt()+1
    val state by viewModel.state.collectAsState()
    Column(Modifier.padding(innerPadding)) {
        Searchbar(
            text = state.searchQuery,
            modifier = Modifier,
            readOnly = false,
            onClick = {},
            onValueChange = {
                viewModel.onIntent(SearchIntents.searchQueryInput(it))
                viewModel.onIntent(SearchIntents.searchingNews(it))
            })
        Spacer(Modifier.height(10.dp))
        if (state.articles.isEmpty()) {
            Text("Start searching items")
        }
         if (state.error){
             Toast.makeText(contex,"Error while loading", Toast.LENGTH_SHORT).show()
         }
         else{
            LazyColumn {
                if(state.isSearching){
                    items(shimmerItemCount) {
                        ShimmerNewsCardPlaceholder()
                    }
                }else{
                    items(state.articles) { article ->
                        NewsCard(article, onClick = {
                            viewModel.onIntent(SearchIntents.newsClick(article))
                        })

                    }
                }
            }
        }
    }
}