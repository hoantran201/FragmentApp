package com.example.fragmentapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentapp.databinding.FragmentNotificationBinding
import com.example.fragmentapp.ui.adapter.PostAdapter
import com.example.fragmentapp.viewmodel.post.PostViewModel
import org.koin.android.ext.android.inject

class NotificationFragment : Fragment() {

    private var binding: FragmentNotificationBinding? = null
    private val postViewModel by inject<PostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            var recyclerView = it.rvNotify
            recyclerView.layoutManager = LinearLayoutManager(context)
            postViewModel.post.observe(viewLifecycleOwner, Observer { posts ->
                recyclerView.adapter = PostAdapter(posts)
            })
        }
    }
}