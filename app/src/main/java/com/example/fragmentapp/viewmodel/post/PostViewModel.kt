package com.example.fragmentapp.viewmodel.post

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fragmentapp.model.PostModel
import java.net.UnknownHostException

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val posts = liveData {
        try{
            val data = postRepository.getPosts()
            emit(data)
        } catch(e: UnknownHostException){
            emit(emptyList())
            Log.e("Error: ", e.toString())
        }

    }

    val post = posts
}