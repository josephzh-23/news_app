package com.plcoding.koinguide


data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)