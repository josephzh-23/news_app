package com.example.newsapp.News

import com.plcoding.koinguide.ui.NewsResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val api: MyApi
) : MainRepository {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int,
        apiKey: String
    ): Result<NewsResponse> = flow {

        emit(Result.success(api.getBreakingNews(countryCode, pageNumber)))
    }.catch {
        emit(Result.failure(it))
    }.first()
}











