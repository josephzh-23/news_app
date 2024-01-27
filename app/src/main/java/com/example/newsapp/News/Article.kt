package com.example.newsapp.News


data class Article(
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String?= null,
    val url: String?=null,
    val urlToImage: String
)