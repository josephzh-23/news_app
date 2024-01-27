package com.example.newsapp.Comments

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path


interface CommentsApi {
    @GET("posts")
    suspend fun getPosts():  List<Post>

    @GET("posts/{id}/comments")
    suspend fun getComments(
        @Path("id") id: Int
    ):List<Comment>
}