package com.example.fragmentapp.ui.create

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowMetrics
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.fragmentapp.InterstitialManager
import com.example.fragmentapp.databinding.ActivityCreateBinding
import com.example.fragmentapp.ui.reward.RewardActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

class CreateActivity : ComponentActivity() {

    private var mAdView: AdView? = null
    private var nativeAd: NativeAd? = null
    private var nativeAdView: NativeAdView? = null
    private var mediaView: MediaView? = null
    private var binding: ActivityCreateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val interstitialManager = InterstitialManager(this)

        binding = ActivityCreateBinding.inflate(layoutInflater)
        binding?.let {
            setContentView(it.root)
            mAdView = AdView(this)
            mAdView?.adUnitId = "ca-app-pub-3940256099942544/9214589741"
            mAdView?.setAdSize(adSizeBanner())

            it.flBannerAd.removeAllViews()
            it.flBannerAd.addView(mAdView)

            it.topBar.btnAdd.setOnClickListener {
                interstitialManager.interstitialShow()
                val intent = Intent(this, RewardActivity::class.java)
                startActivity(intent)
            }

            it.topBar.btnBack.setOnClickListener {
                onBackPressed()
                finish()
            }

            adBanner()
            loadNativeAd()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdView?.destroy()
    }


    fun adSizeBanner(): AdSize {
        val displayMetrics = resources.displayMetrics
        val adWidthPixels =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics: WindowMetrics = this.windowManager.currentWindowMetrics
                windowMetrics.bounds.width()
            } else {
                displayMetrics.widthPixels
            }
        val density = displayMetrics.density
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }

    fun adBanner() {
        val adRequest = AdRequest.Builder().build()
        mAdView?.loadAd(adRequest)
        mAdView?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                Log.d("AdMob", "Ad loaded successfully.")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                super.onAdFailedToLoad(adError)
                Log.d("AdMob", "Ad failed to load with error code: $adError")
            }

            override fun onAdOpened() {
                super.onAdOpened()
                Log.d("AdMob", "Ad opened.")
            }

            override fun onAdClicked() {
                super.onAdClicked()
                Log.d("AdMob", "Ad clicked.")
            }

            override fun onAdClosed() {
                super.onAdClosed()
                Log.d("AdMob", "Ad closed.")
            }
        }
    }

    fun loadNativeAd() {
        val adRequest = AdRequest.Builder().build()
        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/1044960115")
            .forNativeAd { nativeAd ->
                this.nativeAd = nativeAd
                if (this.nativeAd != null) {
                    populateNativeAdView(nativeAd)
                } else print("No native video")
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.e("NativeAd", " ${error.message} ")
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .build()

        adLoader.loadAd(adRequest)
    }

    fun populateNativeAdView(nativeAd: NativeAd) {
        binding?.let {
            val bind = it.iclAdsVideo
            nativeAdView = bind.ntAdView
            mediaView = bind.mdVideo

            nativeAdView?.mediaView = mediaView

            nativeAdView?.headlineView = bind.tvHeadline
            nativeAdView?.bodyView = bind.tvBody
            nativeAdView?.callToActionView = bind.btnAction

            (nativeAdView?.headlineView as TextView).text = nativeAd.headline
            (nativeAdView?.bodyView as TextView).text = nativeAd.body
            (nativeAdView?.callToActionView as Button).text = nativeAd.callToAction

            nativeAdView?.setNativeAd(nativeAd)
        }
    }
}