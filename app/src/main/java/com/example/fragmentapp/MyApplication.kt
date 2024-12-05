package com.example.fragmentapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import com.example.fragmentapp.di.AppModule
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyApplication : Application() {

    var appModule = AppModule().appModule
    private lateinit var appOpenManager: AppOpenManager

    override fun onCreate() {
        super.onCreate()
        appOpenManager = AppOpenManager(this@MyApplication)
        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            MobileAds.initialize(this@MyApplication) {}
        }
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}