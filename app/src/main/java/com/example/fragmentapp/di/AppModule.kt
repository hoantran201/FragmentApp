package com.example.fragmentapp.di

import com.example.fragmentapp.viewmodel.post.PostRepository
import com.example.fragmentapp.service.JobService
import com.example.fragmentapp.viewmodel.post.PostViewModel
import com.example.fragmentapp.viewmodel.job.JobRepository
import com.example.fragmentapp.viewmodel.job.JobRepositoryImpl
import com.example.fragmentapp.viewmodel.job.JobViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf

class AppModule {
    val appModule = module {
        singleOf(::JobRepositoryImpl) { bind<JobRepository>() }
        singleOf(::JobService)
        singleOf(::PostRepository)
        factoryOf(::JobViewModel)
        factoryOf(::PostViewModel)
    }
}