package com.plcoding.koinguide.ui

import com.example.newsapp.News.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)