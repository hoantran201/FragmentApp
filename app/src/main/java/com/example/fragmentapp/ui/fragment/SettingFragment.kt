package com.example.fragmentapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentapp.databinding.FragmentSettingBinding
import com.example.fragmentapp.entity.User
import com.example.fragmentapp.ui.adapter.SettingAdapter
import com.example.fragmentapp.viewmodel.setting.SettingViewModel
import org.koin.android.ext.android.inject
import kotlin.getValue

class SettingFragment : Fragment() {

    private var binding: FragmentSettingBinding? = null
    private val settingViewModel by inject<SettingViewModel>()
    private var userList = mutableListOf<User>()
    private val settingAdapter by lazy(LazyThreadSafetyMode.NONE) { SettingAdapter(userList) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            var recyclerView = it.rvSetting
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = settingAdapter

            settingViewModel.allUsers.observe(viewLifecycleOwner, Observer { users ->
                userList.apply {
                    clear()
                    addAll(users)
                }
                settingAdapter.notifyDataSetChanged()
            })

            it.formData.btnAddData.setOnClickListener {
                addData()
            }
        }
    }

    fun addData() {
        binding?.let {

            var txtFirstName = it.formData.edtFirstName
            var txtLastName = it.formData.edtLastName
            var txtAge = it.formData.edtAge
            var txtJob = it.formData.edtJob

            var firstName = txtFirstName.text.toString()
            var lastName = txtLastName.text.toString()
            var age = txtAge.text.toString()
            var job = txtJob.text.toString()

            val user = User(
                firstName = firstName,
                lastName = lastName,
                age = age,
                job = job
            )
            settingViewModel.insertUser(user)

            txtFirstName.text.clear()
            txtLastName.text.clear()
            txtAge.text.clear()
            txtJob.text.clear()
        }
    }
}