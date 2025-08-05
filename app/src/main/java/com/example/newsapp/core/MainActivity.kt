package com.example.newsapp.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newsapp.core.ui.NewsAppTheme
import com.example.newsapp.features.navigation.BottomNavBar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
//        val dao= DatabaseProvider.getDb(this).newsDao()
//        lifecycleScope.launch {
//            dao.insertToFav(
//                Article(
//                    author = "Eva",
//                    content = "Test Content",
//                    description = "Testing",
//                    publishedAt = "2025-08-02",
//                    source = Source(name = "ChatGPT"),
//                    title = "Test Article",
//                    url = "https://test.com",
//                    urlToImage = null
//                )
//            )
//            dao.getNews().collect {
//                Log.d("Room", it.toString())
//            }
//        }
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BottomNavBar(innerPadding)
                }
            }
        }
    }
}