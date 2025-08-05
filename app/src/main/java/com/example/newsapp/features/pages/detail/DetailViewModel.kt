package com.example.newsapp.features.pages.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.data.roomdb.DatabaseProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val db= DatabaseProvider.getDb(application)
    val dao=db.newsDao()
    private val _state= MutableStateFlow(DetailState())
    val state=_state.asStateFlow()

    init {
        isBookmarked(_state.value.articleInfo)
    }

    fun onIntent(intent: DetailIntent){
        when(intent){
            is DetailIntent.addToFavourite -> addToFav(intent.article)
            is DetailIntent.removeFromFavourite -> removeFromFav(intent.article)
            is DetailIntent.CheckIfBookmarked -> isBookmarked(intent.article)
        }
    }

    private fun removeFromFav(art: Article) {
        viewModelScope.launch {
            _state.update {
                it.copy(error = false, isFavourite = false, articleInfo = null)
            }
            dao.deleteFromFav(art)
            Log.d("removed","Item ${art.source} removed")
        }
    }

    private fun addToFav(art: Article) {
        viewModelScope.launch {
            _state.update {
                it.copy(error = false, isFavourite = true, articleInfo = art)
            }
            val existing=dao.getArticleByUrl(art.url)
            if (existing==null){
                dao.insertToFav(art)
            }
            Log.d("added","Item ${art.source} added")

        }
    }
    private fun isBookmarked(article: Article?){
        viewModelScope.launch {
            dao.getNews().collect {bookmarked->
                val isBookmarked = bookmarked.any {
                    it.url== article?.url
                }
                _state.update {
                    it.copy(error = false, articleInfo = article, isFavourite = isBookmarked)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        db.close()
    }

}