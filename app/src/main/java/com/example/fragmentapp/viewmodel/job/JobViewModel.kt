package com.example.fragmentapp.viewmodel.job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentapp.model.JobModel

class JobViewModel(private val jobRepository: JobRepository) : ViewModel() {

    private val jobLiveData = MutableLiveData<List<JobModel>>()

    fun getJobData(): LiveData<List<JobModel>> {
        jobLiveData.postValue(jobRepository.getJob())
        return jobLiveData
    }

    fun addJobData(name: String, jobName: String) {
        jobLiveData.postValue(jobRepository.addJob(name, jobName))
    }
}