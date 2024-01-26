package com.plcoding.koinguide.ui

import com.plcoding.koinguide.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)