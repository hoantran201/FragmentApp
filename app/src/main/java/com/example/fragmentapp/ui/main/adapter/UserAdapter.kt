package com.example.fragmentapp.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fragmentapp.R
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentapp.model.UserModel

class UserAdapter(private val users: List<UserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.txtName.text = user.name
        holder.txtAge.text= user.age.toString()
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtAge: TextView = view.findViewById(R.id.txtAge)
    }
}
