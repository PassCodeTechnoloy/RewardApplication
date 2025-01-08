package com.thorient.quiz.ui;

import static com.thorient.quiz.ads.RewardedAds.LoadRewardedAds;
import static com.thorient.quiz.ads.RewardedAds.rewardedAd;
import static com.thorient.quiz.ads.RewardedAds.showRewardedAds;
import static com.thorient.quiz.prefrence.SharedPrefsUtil.getValue;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.thorient.quiz.R;
import com.thorient.quiz.Repository.RewardViewModel;
import com.thorient.quiz.adpter.ViewPagerAdapter;
import com.thorient.quiz.ads.native_ads.LoadNativeAds;
import com.thorient.quiz.ads.native_ads.TemplateView;
import com.thorient.quiz.model.CategoryResponse;
import com.thorient.quiz.prefrence.SharedPrefsUtil;

public class MainActivity extends AppCompatActivity {

    private View include;
    private ImageView menu_icn;
    private TextView tv_coin;
    private RewardViewModel rewardViewModel;
    private DrawerLayout my_drawer_layout;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private NativeAdView nativeAdView;
    private ShimmerFrameLayout shimmerFrameLayout;
    private TemplateView template;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, initializationStatus -> {
        });
        initView();
        LoadNativeAds.loadNativeAds(this, template, shimmerFrameLayout);
        if (getValue(this, "coin", 0) < 0) {
            AdsDialog().show();
        }

        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
        rewardViewModel.getCategory().observe(this, new Observer<CategoryResponse>() {
            @Override
            public void onChanged(CategoryResponse categoryResponse) {
                if (categoryResponse != null) {
                    viewPager2.setAdapter(new ViewPagerAdapter(MainActivity.this, categoryResponse.getData(),tv_coin));
                    new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
                        tab.setText(categoryResponse.getData().get(position).getName());
                    }).attach();
                }
            }
        });
        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_drawer_layout.openDrawer(GravityCompat.START);
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    my_drawer_layout.closeDrawer(GravityCompat.START);

                }

            }
        });
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    my_drawer_layout.closeDrawer(GravityCompat.START);

                }
            }
        });
        findViewById(R.id.rendome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    my_drawer_layout.closeDrawer(GravityCompat.START);

                }
            }
        });
        findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    my_drawer_layout.closeDrawer(GravityCompat.START);

                }
                startActivity(new Intent(MainActivity.this, Contact_Activity.class));
            }
        });
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    my_drawer_layout.closeDrawer(GravityCompat.START);

                }
            }
        });
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    my_drawer_layout.closeDrawer(GravityCompat.START);
                }
                startActivity(new Intent(MainActivity.this, Login_Activity.class));

            }
        });
    }

    public Dialog AdsDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.ads_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LinearLayout llCancel = dialog.findViewById(R.id.llCancel);
        LinearLayout llWatchAds = dialog.findViewById(R.id.llWatchAds);
        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        llWatchAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO show rewarded ads and give them reward coins*/
                dialog.dismiss();
                showRewardedAds(MainActivity.this, tv_coin);
            }
        });
        return dialog;
    }

    private void initView() {
        include = findViewById(R.id.include);
        menu_icn = include.findViewById(R.id.menu_icn);
        tv_coin = include.findViewById(R.id.tv_coin);
        viewPager2 = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        my_drawer_layout = findViewById(R.id.my_drawer_layout);
        nativeAdView = findViewById(R.id.native_ad_view);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        template = findViewById(R.id.my_template);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_coin.setText(String.valueOf(getValue(this, "coin", 0)));
    }
}