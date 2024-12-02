package com.example.fragmentapp.api.repository

import com.example.fragmentapp.api.RetrofitClient
import com.example.fragmentapp.model.PostModel

class PostRepository {

    suspend fun getPosts(): List<PostModel> {
        return RetrofitClient.apiService.getPosts()
    }
}