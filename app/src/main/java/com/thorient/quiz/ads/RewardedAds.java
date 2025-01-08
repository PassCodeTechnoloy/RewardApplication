package com.thorient.quiz.ads;

import static com.thorient.quiz.prefrence.SharedPrefsUtil.getValue;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.thorient.quiz.prefrence.SharedPrefsUtil;
import com.thorient.quiz.ui.MainActivity;

public class RewardedAds {
    public static RewardedAd rewardedAd;
    private static final String TAG = "load_ads";
    public static void LoadRewardedAds(Activity context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.toString());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        Log.d(TAG, "Ad was loaded.");

                    }
                });

    }

    public static void showRewardedAds(Activity activity, TextView tv_coin) {
        if (rewardedAd != null) {
            rewardedAd.show(activity, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    SharedPrefsUtil.increaseValue(activity, "coin", 100);
                    LoadRewardedAds(activity);
                    if (tv_coin!=null)
                        tv_coin.setText(String.valueOf(getValue(activity, "coin", 0)));
                }
            });
        } else {
            LoadRewardedAds(activity);
            Toast.makeText(activity, "The rewarded ad wasn't ready yet.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }
    }

}
