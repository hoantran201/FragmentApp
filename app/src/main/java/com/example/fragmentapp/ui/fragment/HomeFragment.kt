package com.example.fragmentapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentapp.databinding.FragmentHomeBinding
import com.example.fragmentapp.ui.main.adapter.UserAdapter
import com.example.fragmentapp.viewmodel.UserViewModel

open class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var recyclerView: RecyclerView? =null

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {

            recyclerView = binding?.rvUser
            recyclerView?.layoutManager = LinearLayoutManager(requireContext())

            userViewModel.getUsers().observe(viewLifecycleOwner, Observer { users ->
                val adapter = UserAdapter(users)
                recyclerView?.adapter = adapter
            })

            it.formInfo.btnSubmit.setOnClickListener {
                addUser()
            }
        }
    }

    fun addUser() {
        binding?.let{

            var txtName: EditText = it.formInfo.edtName
            var txtAge: EditText = it.formInfo.edtAge

            var name = txtName.text.toString()
            var age = txtAge.text.toString().toIntOrNull()?:run{18}
            userViewModel.setUser(name, age)

            txtName.text.clear()
            txtAge.text.clear()
        }

    }
}