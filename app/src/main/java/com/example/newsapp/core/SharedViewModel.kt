package com.example.newsapp.core

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.Article

class SharedViewModel: ViewModel() {
    private val _article= mutableStateOf<Article?>(null)
    val article=_article

    fun selectArticle(article: Article){
        _article.value=article
    }

}