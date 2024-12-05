package com.example.fragmentapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.example.fragmentapp.ui.main.MainActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppOpenManager(private val myApplication: MyApplication) :
    Application.ActivityLifecycleCallbacks {
    private var appOpenAd: AppOpenAd? = null
    private var isShowingAd = false

    init {
        myApplication.registerActivityLifecycleCallbacks(this)
        loadAd()
    }

    fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        AppOpenAd.load(
            myApplication,
            "ca-app-pub-3940256099942544/9257395921",
            adRequest,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e("No Load Ads Open App", "$loadAdError")
                }
            }
        )
    }

    fun showAdIfAvailable(activity: Activity) {
        if (isShowingAd || appOpenAd == null) {
            loadAd()
            return
        }
        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                appOpenAd = null
                isShowingAd = false
                loadAd()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Log.e("No Load Ads Open App", "$adError")
            }

            override fun onAdShowedFullScreenContent() {
                isShowingAd = true
            }
        }
        try {
            appOpenAd?.show(activity)
        } catch (e: Exception) {
            println("Error Ads Open App: ${e.message}")
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {
        if (activity is MainActivity) {
            val backgroundScope = CoroutineScope(Dispatchers.IO)
            backgroundScope.launch {
                delay(4000)
                if (!isShowingAd && appOpenAd != null) {
                    withContext(Dispatchers.Main) {
                        showAdIfAvailable(activity)
                    }
                }
            }
        }
    }

    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}
}
