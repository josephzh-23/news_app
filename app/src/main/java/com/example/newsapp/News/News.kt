package com.example.newsapp.News


data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)