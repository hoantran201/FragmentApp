package com.example.fragmentapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentapp.databinding.ItemUserBinding
import com.example.fragmentapp.model.JobModel

class InfoAdapter(private var jobs: List<JobModel>) :
    RecyclerView.Adapter<InfoAdapter.JobViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(jobs[position])
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    class JobViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobModel) {
            binding.txtName.text = job.name
            binding.txtAge.text = job.jobName
        }
    }
}