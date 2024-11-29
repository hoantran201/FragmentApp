package com.example.fragmentapp.viewmodel.job

import com.example.fragmentapp.model.JobModel
import com.example.fragmentapp.model.service.JobService
interface JobRepository {
    fun getJob(): List<JobModel>
    fun addJob(name: String, jobName: String): List<JobModel>
}
class JobRepositoryImpl(jobService: JobService) : JobRepository {

    var jobList = jobService.jobList

    override fun getJob(): List<JobModel> {
        return jobList
    }

    override fun addJob(name: String, jobName: String): List<JobModel> {
        jobList.add(JobModel(name, jobName))
        return jobList
    }
}