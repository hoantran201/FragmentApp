package com.example.fragmentapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentapp.databinding.ItemDataBinding
import com.example.fragmentapp.entity.User
import com.example.fragmentapp.model.UserModel

class SettingAdapter(private var users: List<User>) :
    RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class SettingViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.txtFirstName.text = user.firstName
            binding.txtLastName.text = user.lastName
            binding.txtAge.text = user.age
            binding.txtJob.text = user.job
        }
    }

    fun updateData(usersList: List<User>) {
        users = usersList
    }
}