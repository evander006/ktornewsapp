package com.example.newsapp.data.api

import com.example.newsapp.data.Article
import com.example.newsapp.data.NewsResponse
import com.example.newsapp.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install
import kotlinx.serialization.json.Json


class Client: NewsApi {
    fun getClient(): HttpClient= HttpClient {

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(DefaultRequest){
            url{
                host= Constants.URL
                protocol= URLProtocol.HTTPS
            }
        }
        install(HttpTimeout){
            socketTimeoutMillis=8000
            requestTimeoutMillis=8000
            connectTimeoutMillis=8000
        }
    }

    override suspend fun getNews(): List<Article> {
        val response: NewsResponse = getClient().get("/v2/everything") {
            parameter("apiKey", Constants.API_KEY)
            parameter("sources", "bbc-news,cnn,the-verge") // ⚠️ выбери доступные
        }.body()
        return response.articles
    }


    override suspend fun searchNews(q: String): List<Article> {
        val response: NewsResponse = getClient().get("/v2/everything"){
            parameter("apiKey", Constants.API_KEY)
            parameter("q", q)
        }.body()
        return response.articles
    }

}