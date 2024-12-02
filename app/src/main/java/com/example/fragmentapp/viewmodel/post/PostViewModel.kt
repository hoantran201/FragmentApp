package com.example.fragmentapp.viewmodel.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    val posts = liveData {
        val data = postRepository.getPosts()
        emit(data)
    }
}