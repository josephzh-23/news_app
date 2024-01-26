package com.plcoding.koinguide

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
//        emit(error("an errror "))
        println("${it.message} message")
        println("${it.printStackTrace()} error happened")
    }.first()
}











