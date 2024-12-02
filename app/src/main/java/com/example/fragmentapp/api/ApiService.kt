package com.example.fragmentapp.api

import com.example.fragmentapp.model.PostModel
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>

}