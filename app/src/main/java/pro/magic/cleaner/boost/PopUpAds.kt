package pro.magic.cleaner.boost

import android.content.Context
import android.util.Log

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

import pro.magic.cleaner.boost.Constants.adsShow

object PopUpAds {

//    private var mInterstitialAd: InterstitialAd? = null
//
//    fun ShowInterstitialAds(context: Context) {
//        if (false) {
//
//            if (mInterstitialAd != null) {
//                if (!mInterstitialAd!!.isLoading && !mInterstitialAd!!.isLoaded) {
//                    mInterstitialAd = InterstitialAd(context)
//                    mInterstitialAd!!.adUnitId = "ca-app-pub-9387354664905418/5726107818"
//                    mInterstitialAd!!.loadAd(AdRequest.Builder().build())
//                }
//                if (mInterstitialAd!!.isLoaded) {
//                    mInterstitialAd!!.show()
//                } else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.")
//                }
//            } else {
//                mInterstitialAd = InterstitialAd(context)
//                mInterstitialAd!!.adUnitId = "ca-app-pub-9387354664905418/5726107818"
//                mInterstitialAd!!.loadAd(AdRequest.Builder().build())
//            }
//            if (mInterstitialAd!!.isLoaded) {
//                mInterstitialAd!!.show()
//            } else {
//                Log.d("TAG", "The interstitial wasn't loaded yet.")
//            }
//
//
//            mInterstitialAd!!.adListener = object : AdListener() {
//                override fun onAdLoaded() {
//                    Constants.splashads = true
//                }
//
//                override fun onAdFailedToLoad(errorCode: Int) {
//                    // mInterstitialAd.loadAd(new AdRequest.Builder().build());
//                    Constants.splashads = true
//                }
//
//                override fun onAdOpened() {
//                    // Code to be executed when the ad is displayed.
//                    Constants.splashads = true
//                }
//
//                override fun onAdLeftApplication() {
//                    // Code to be executed when the user has left the app.
//                    Constants.splashads = true
//                }
//
//                override fun onAdClosed() {
//                    mInterstitialAd!!.loadAd(AdRequest.Builder().build())
//                    Constants.splashads = true
//                }
//            }
//        }
//    }
}
