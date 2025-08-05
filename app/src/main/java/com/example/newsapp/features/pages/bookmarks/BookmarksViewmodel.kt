package com.example.newsapp.features.pages.bookmarks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.roomdb.DatabaseProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarksViewmodel(application: Application): AndroidViewModel(application) {
    private val _state= MutableStateFlow(BookmarksState())
    val state = _state.asStateFlow()
    private val db= DatabaseProvider.getDb(application)
    private val dao=db.newsDao()

    init {
        loadNews()
    }

    private fun loadNews(){
        viewModelScope.launch {
            dao.getNews().collect {bookmarked->
                _state.update {
                    it.copy(error = false, isLoading = false, articles = bookmarked)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        db.close()
    }

}