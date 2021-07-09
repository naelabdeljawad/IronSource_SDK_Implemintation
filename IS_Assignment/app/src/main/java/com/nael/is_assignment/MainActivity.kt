package com.nael.is_assignment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.impressionData.ImpressionData
import com.ironsource.mediationsdk.impressionData.ImpressionDataListener
import com.ironsource.mediationsdk.integration.IntegrationHelper
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.model.Placement
import com.ironsource.mediationsdk.sdk.InterstitialListener
import com.ironsource.mediationsdk.sdk.RewardedVideoListener

class MainActivity : AppCompatActivity(), RewardedVideoListener, InterstitialListener,
    ImpressionDataListener, View.OnClickListener {
    companion object {
        const val APP_KEY = "85460dcd"
        const val TAG = "IS Activity"
    }

    private lateinit var rewarderedButton: Button
    private lateinit var showInterstitialButton: Button
    private lateinit var loadInterstitialButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IntegrationHelper.validateIntegration(this)
        val advertisingId = IronSource.getAdvertiserId(this@MainActivity)
        initIronSource(advertisingId)
        rewarderedButton = findViewById(R.id.is_reward_button) as Button
        showInterstitialButton = findViewById(R.id.is_show_button) as Button
        loadInterstitialButton = findViewById(R.id.is_load_button) as Button
        initUIElements()
    }

    /**
     * initialize the UI elements of the activity
     */
    private fun initUIElements() {
        rewarderedButton?.setOnClickListener { rewarderedClick() }
        loadInterstitialButton?.setOnClickListener { loadInterstitialClick() }
        showInterstitialButton?.setOnClickListener { showInterstitialClick() }
    }

    private fun rewarderedClick() {
        if (IronSource.isRewardedVideoAvailable())
            IronSource.showRewardedVideo()
    }

    private fun showInterstitialClick() {
        if (IronSource.isInterstitialReady()) {
            IronSource.showInterstitial()
        }
    }

    private fun loadInterstitialClick() {
        IronSource.loadInterstitial()
    }

    private fun initIronSource(userId: String?) {
        // set the IronSource rewarded video listener
        IronSource.setRewardedVideoListener(this)
        // set the interstitial listener
        IronSource.setInterstitialListener(this)
        // add impression data listener
        IronSource.addImpressionDataListener(this)
        // set the IronSource user id
        IronSource.setUserId(userId)
        // init the IronSource SDK
        IronSource.init(this, APP_KEY);
        IntegrationHelper.validateIntegration(this@MainActivity);
    }

    override fun onResume() {
        super.onResume()
        IronSource.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        IronSource.onPause(this)
    }

    override fun onRewardedVideoAdClosed() {
        Log.d(TAG, "onRewardedVideoAdClosed")
    }

    override fun onRewardedVideoAdRewarded(p0: Placement?) {
        Log.d(TAG, "onRewardedVideoAdRewarded")
    }

    override fun onRewardedVideoAdClicked(p0: Placement?) {
        Log.d(TAG, "onRewardedVideoAdClicked")
    }

    override fun onRewardedVideoAdOpened() {
        Log.d(TAG, "onRewardedVideoAdOpened")
    }

    override fun onRewardedVideoAdShowFailed(p0: IronSourceError?) {
        Log.d(TAG, "onRewardedVideoAdShowFailed $p0")
    }

    override fun onRewardedVideoAvailabilityChanged(p0: Boolean) {
        Log.d(TAG, "onRewardedVideoAvailabilityChanged $p0")
    }

    override fun onRewardedVideoAdEnded() {
        Log.d(TAG, "onRewardedVideoAdEnded")
    }

    override fun onRewardedVideoAdStarted() {
        Log.d(TAG, "onRewardedVideoAdStarted")
    }

    override fun onInterstitialAdLoadFailed(p0: IronSourceError?) {
        Log.d(TAG, "onInterstitialAdLoadFailed")
    }

    override fun onInterstitialAdClosed() {
        Log.d(TAG, "onInterstitialAdClosed")
    }

    override fun onInterstitialAdShowFailed(p0: IronSourceError?) {
        Log.d(TAG, "")
    }

    override fun onInterstitialAdClicked() {
        Log.d(TAG, "onInterstitialAdShowFailed")
    }

    override fun onInterstitialAdReady() {
        Log.d(TAG, "onInterstitialAdReady")
    }

    override fun onInterstitialAdOpened() {
        Log.d(TAG, "onInterstitialAdOpened")
    }

    override fun onInterstitialAdShowSucceeded() {
        Log.d(TAG, "onInterstitialAdShowSucceeded")
    }

    override fun onImpressionSuccess(impressionData: ImpressionData?) {
        if (impressionData != null) {
            Log.d(TAG, "onImpressionSuccess $impressionData")
        }
    }

    override fun onClick(v: View?) {
        // check if video is available
        //if (IronSource.isRewardedVideoAvailable()) //show rewarded video
        //    IronSource.showRewardedVideo()
    }


}