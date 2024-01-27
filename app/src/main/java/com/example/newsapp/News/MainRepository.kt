package com.example.newsapp.News

import com.plcoding.koinguide.ui.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainRepository {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):  Result<NewsResponse>
}