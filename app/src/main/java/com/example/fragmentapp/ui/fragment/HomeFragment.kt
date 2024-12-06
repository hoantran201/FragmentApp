package com.example.fragmentapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentapp.databinding.FragmentHomeBinding
import com.example.fragmentapp.model.UserModel
import com.example.fragmentapp.ui.adapter.UserAdapter
import com.example.fragmentapp.viewmodel.user.UserViewModel

open class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val userViewModel by viewModels<UserViewModel>()
    private var userList = mutableListOf<UserModel>()
    private val userAdapter by lazy  (LazyThreadSafetyMode.NONE) { UserAdapter(userList) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            var recyclerView = it.rvUser
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = userAdapter

            userViewModel.getUsers().observe(viewLifecycleOwner, Observer { users ->
                userList.apply {
                    clear()
                    addAll(users)
                }
                userAdapter.notifyDataSetChanged()
            })

            it.formInfo.btnSubmit.setOnClickListener {
                addUser()
            }
        }
    }

    fun addUser() {
        binding?.let {

            var txtName: EditText = it.formInfo.edtName
            var txtAge: EditText = it.formInfo.edtAge

            var name = txtName.text.toString()
            var age = txtAge.text.toString().toIntOrNull() ?: run { 18 }
            userViewModel.setUser(name, age)

            txtName.text.clear()
            txtAge.text.clear()
        }

    }
}