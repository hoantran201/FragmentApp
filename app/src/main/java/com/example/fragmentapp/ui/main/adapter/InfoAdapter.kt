package com.example.fragmentapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentapp.R
import com.example.fragmentapp.model.JobModel

class InfoAdapter(private val jobs: List<JobModel>) : RecyclerView.Adapter<InfoAdapter.JobViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]
        holder.txtName.text = job.name
        holder.txtJobName.text = job.jobName
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtJobName: TextView = view.findViewById(R.id.txtAge)
    }
}