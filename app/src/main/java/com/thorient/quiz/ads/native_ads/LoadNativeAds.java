package com.thorient.quiz.ads.native_ads;

import android.content.Context;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.nativead.NativeAd;
import com.thorient.quiz.R;

public class LoadNativeAds {
    public static void loadNativeAds(Context context, TemplateView view, ShimmerFrameLayout shimmerFrameLayout) {
        shimmerFrameLayout.startShimmer();
        AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new NativeTemplateStyle.Builder().build();
//                        TemplateView template = findViewById(R.id.my_template);
                        view.setStyles(styles);
                        view.setNativeAd(nativeAd);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

}
