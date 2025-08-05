package com.example.newsapp.features.pages.home

import android.widget.Toast
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.data.Article
import com.example.newsapp.features.effects.Searchbar
import com.example.newsapp.features.effects.ShimmerNewsCardPlaceholder
import com.example.newsapp.features.pages.home.cards.NewsCard

@Composable
fun HomeScreenUI(innerPadding: PaddingValues,
                 viewModel: HomeViewModel,
                 onSearchbarClick:()-> Unit,
                 goToDetailScreen:(Article)->Unit
) {
    val context= LocalContext.current
    val state by viewModel.state.collectAsState()

    val screenHeightDp= LocalConfiguration.current.screenHeightDp
    val estimatedCardHeight=120
    val shimmerItemCount = (screenHeightDp / estimatedCardHeight).toInt()+1
    var textInput by remember { mutableStateOf("") }
    var isReadOnly by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        viewModel.onIntent(HomeIntents.LoadNews)
    }
    if (state.error){
        Toast.makeText(context, "Error while loading", Toast.LENGTH_SHORT).show()
    }

    Column(modifier = Modifier.padding(innerPadding)) {
        Searchbar(
            text = textInput,
            modifier = Modifier,
            readOnly = isReadOnly,
            onClick = {onSearchbarClick()},
            onValueChange = {},
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = state.articles.joinToString("  ⚡  ") { it.title },
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .basicMarquee()
        )
        Spacer(Modifier.height(10.dp))
        LazyColumn {
            if (state.isLoading) {
                items(shimmerItemCount) {
                    ShimmerNewsCardPlaceholder()
                }
            } else {
                items(state.articles) { article ->
                    NewsCard(article, onClick = {
                        goToDetailScreen(article)
                    })

                }
            }
        }
    }

}