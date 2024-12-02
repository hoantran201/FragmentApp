package com.example.fragmentapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentapp.databinding.FragmentNotificationBinding
import com.example.fragmentapp.ui.main.adapter.PostAdapter
import com.example.fragmentapp.viewmodel.api.PostViewModel
import org.koin.android.ext.android.inject

class NotificationFragment : Fragment() {

    private var binding: FragmentNotificationBinding? = null
    private var recyclerView: RecyclerView? = null
    private val postViewModel: PostViewModel by inject()

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
            recyclerView = binding?.rvNotify
            recyclerView?.layoutManager = LinearLayoutManager(requireContext())

            postViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
                recyclerView?.adapter = PostAdapter(posts)

            })

        }
    }
}