package com.example.fragmentapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentapp.databinding.FragmentNotificationBinding
import com.example.fragmentapp.model.PostModel
import com.example.fragmentapp.ui.adapter.PostAdapter
import com.example.fragmentapp.viewmodel.post.PostViewModel
import org.koin.android.ext.android.inject

class NotificationFragment : Fragment() {

    private var binding: FragmentNotificationBinding? = null
    private val postViewModel by inject<PostViewModel>()
    private var postList = mutableListOf<PostModel>()
    private val postAdapter by lazy(LazyThreadSafetyMode.NONE) { PostAdapter(postList) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            var recyclerView = it.rvNotify
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = postAdapter

            postViewModel.post.observe(viewLifecycleOwner, Observer { posts ->
                postList.apply {
                    clear()
                    addAll(posts)
                }
                postAdapter.notifyDataSetChanged()
            })
        }
    }
}