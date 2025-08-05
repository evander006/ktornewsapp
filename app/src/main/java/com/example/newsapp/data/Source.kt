package com.example.newsapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String? = null,
    val name: String
)