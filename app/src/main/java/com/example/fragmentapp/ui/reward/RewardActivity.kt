package com.example.fragmentapp.ui.reward

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import com.example.fragmentapp.databinding.ActivityRewardBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RewardActivity : ComponentActivity() {

    private var binding: ActivityRewardBinding? = null
    private var rewardedAd: RewardedAd? = null
    private val tag = "RewardActivity"
    private var point = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRewardBinding.inflate(layoutInflater)
        binding?.let {
            setContentView(it.root)

            val toolsBar = it.topBar
            val btnAdd = it.topBar.btnAdd
            btnAdd.visibility = View.GONE

            toolsBar.btnBack.setOnClickListener {
                onBackPressed()
                finish()
            }

            it.btnReward.setOnClickListener {
                loadAd()
                CoroutineScope(Dispatchers.IO).launch {
                    delay(5000)
                    withContext(Dispatchers.Main) {
                        populateRewardAdView()
                        showRewardAd()
                    }
                }
            }
        }

    }

    fun loadAd() {
        var adRequest = AdRequest.Builder().build()
        RewardedAd.load(
            this,
            "ca-app-pub-3940256099942544/5224354917",
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(tag, adError.toString())
                    rewardedAd = null
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.d(tag, "Ad was loaded.")
                    rewardedAd = ad
                }
            })
    }

    fun populateRewardAdView() {
        rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                Log.d(tag, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                Log.d(tag, "Ad dismissed fullscreen content.")
                rewardedAd = null
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Log.e(tag, "Ad failed to show fullscreen content.")
                rewardedAd = null
            }

            override fun onAdImpression() {
                Log.d(tag, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(tag, "Ad showed fullscreen content.")
            }
        }
    }

    fun showRewardAd() {
        print("\n in ra $rewardedAd \n")
        rewardedAd?.let { ad ->
            ad.show(this, OnUserEarnedRewardListener { rewardItem ->
                print("\n lỗi rồi $rewardedAd \n")
                point = point + 10000
                setPointView()
                Log.d(tag, "User earned the reward.")
            })
        } ?: run {
            Log.d(tag, "The rewarded ad wasn't ready yet.")
        }
    }

    @SuppressLint("SetTextI18n")
    fun setPointView() {
        binding?.let {
            it.tvReward.text = "Reward: $point USD"
        }
    }
}