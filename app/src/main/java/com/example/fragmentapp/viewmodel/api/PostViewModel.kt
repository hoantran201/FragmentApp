package com.example.fragmentapp.viewmodel.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fragmentapp.api.repository.PostRepository

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    val posts = liveData {
        val data = postRepository.getPosts()
        emit(data)
    }
}