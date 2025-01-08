package com.thorient.quiz.ui;

import static com.thorient.quiz.ads.native_ads.LoadNativeAds.loadNativeAds;
import static com.thorient.quiz.prefrence.SharedPrefsUtil.saveBoolValue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.thorient.quiz.R;
import com.thorient.quiz.ads.native_ads.TemplateView;
import com.thorient.quiz.prefrence.SharedPrefsUtil;

public class IntroOneActivity extends AppCompatActivity {
    private TextView tvOption1, tvOption2, tvOption3, tvOption4;
    private ShimmerFrameLayout shimmerFrameLayout;
    private TemplateView template;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_one);
        initView();
        loadNativeAds(this, template, shimmerFrameLayout);
        tvOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOption1.setBackgroundResource(R.drawable.intro_select_bg);
                nextActivity();
                SharedPrefsUtil.decreaseValue(IntroOneActivity.this, "coin", 100);
            }
        });
        tvOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO Add 100 coine If answer is correct*/
                tvOption2.setBackgroundResource(R.drawable.intro_select_bg_true);
                nextActivity();
                SharedPrefsUtil.increaseValue(IntroOneActivity.this, "coin", 100);
            }
        });
        tvOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOption3.setBackgroundResource(R.drawable.intro_select_bg);
                nextActivity();
                SharedPrefsUtil.decreaseValue(IntroOneActivity.this, "coin", 100);
            }
        });
        tvOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOption4.setBackgroundResource(R.drawable.intro_select_bg);
                nextActivity();
                SharedPrefsUtil.decreaseValue(IntroOneActivity.this, "coin", 100);
            }
        });
    }

    private void nextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroOneActivity.this, IntroTwoActivity.class));
                finish();
            }
        }, 100);
    }


    private void initView() {
        tvOption1 = findViewById(R.id.tvOption1);
        tvOption2 = findViewById(R.id.tvOption2);
        tvOption3 = findViewById(R.id.tvOption3);
        tvOption4 = findViewById(R.id.tvOption4);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        template = findViewById(R.id.my_template);

    }


}
