package com.plcoding.koinguide.ui

import com.example.newsapp.News.Article

sealed class NewsState {
    data class NewsResponse(
        val articles: List<Article>,
        // Will then use these 2 when we get to it
//        val status: String,
//        val totalResults: Int
    ) : NewsState()

    object Loading: NewsState()
    object Error: NewsState()

}
