package com.example.fragmentapp.api

import com.example.fragmentapp.model.PostModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>

    @POST("posts")
    suspend fun createPost(@Body post: PostModel): PostModel
}