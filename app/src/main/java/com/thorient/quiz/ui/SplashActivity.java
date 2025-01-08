package com.thorient.quiz.ui;

import static com.thorient.quiz.ads.RewardedAds.LoadRewardedAds;
import static com.thorient.quiz.prefrence.SharedPrefsUtil.loadBoolValue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.thorient.quiz.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LoadRewardedAds(this);
//        setValue(this, "coin", 5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!loadBoolValue(SplashActivity.this)) {
                    startActivity(new Intent(SplashActivity.this, IntroOneActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, 3000);
    }
}
