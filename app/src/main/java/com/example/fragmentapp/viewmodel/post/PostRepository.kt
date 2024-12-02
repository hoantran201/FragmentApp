package com.example.fragmentapp.viewmodel.post

import com.example.fragmentapp.api.RetrofitClient
import com.example.fragmentapp.model.PostModel

class PostRepository {

    suspend fun getPosts(): List<PostModel> {
        return RetrofitClient.apiService.getPosts()
    }
}