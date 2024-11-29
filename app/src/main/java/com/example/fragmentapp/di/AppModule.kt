package com.example.fragmentapp.di

import com.example.fragmentapp.model.service.JobService
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
        factoryOf(::JobViewModel)
    }
}