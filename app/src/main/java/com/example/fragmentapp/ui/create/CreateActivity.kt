package com.example.fragmentapp.ui.create

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.fragmentapp.InterstitialManager
import com.example.fragmentapp.databinding.ActivityCreateBinding
import com.example.fragmentapp.ui.reward.RewardActivity
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
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

            it.topBar.btnAdd.setOnClickListener {
                interstitialManager.interstitialShow()
                val intent = Intent(this, RewardActivity::class.java)
                startActivity(intent)
            }

            it.topBar.btnBack.setOnClickListener {
                onBackPressed()
                finish()
            }
            mAdView = it.adBanner

            adBanner()
            loadNativeAd()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdView?.destroy()
    }

    fun adBanner() {
        val adRequest = AdRequest.Builder().build()
        mAdView?.loadAd(adRequest)
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
            .withAdListener(object : com.google.android.gms.ads.AdListener() {
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