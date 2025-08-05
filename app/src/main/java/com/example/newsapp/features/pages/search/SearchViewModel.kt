package com.example.newsapp.features.pages.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.data.api.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    var _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun onIntent(intent: SearchIntents){
        when(intent) {
            is SearchIntents.newsClick -> newsClicked(intent.article)
            is SearchIntents.searchQueryInput -> {
                _state.update {
                    it.copy(searchQuery = intent.queryInput)
                }
            }

            is SearchIntents.searchingNews -> searchNews(intent.query)
        }
    }

    private fun newsClicked(article: Article){
        Log.i("clicked", "article $article in searchscreen clicked")

    }
    private fun searchNews(q: String){
        _state.update {
            it.copy(error = false, isSearching = true)
        }
        viewModelScope.launch {
            val news=try {
                Client().searchNews(q)

            }catch (e: Exception){
                emptyList<Article>().also {
                    _state.update {
                        it.copy(error = true, isSearching = false)
                    }
                }

            }
            if (news.isNotEmpty()){
                _state.update {
                    it.copy(isSearching = false, error = false, articles = news)
                }
            }
        }
    }
}