package com.example.newsapp.features.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.data.api.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _state= MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onIntent(intent: HomeIntents){
        when(intent){
            is HomeIntents.isFailedLoad -> isLoadFail()
            HomeIntents.LoadNews -> loadNews()
        }
    }
    private fun loadNews(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, error = false)
            }
            val newsArticles= Client().getNews()
            _state.update {
                it.copy(isLoading = false, error = false, articles = newsArticles)
            }
        }
    }

    private fun isLoadFail(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = false, error = true, articles = emptyList())
            }
        }
    }
}