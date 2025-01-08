package com.thorient.quiz.dialog;

import static com.thorient.quiz.ads.RewardedAds.showRewardedAds;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thorient.quiz.R;
import com.thorient.quiz.ui.MainActivity;

public class RewardDialog extends Dialog {
    private Activity activity;
    private TextView tv_coin;

    public RewardDialog(@NonNull Context context, Activity activity, TextView tv_coin) {
        super(context);
        this.activity = activity;
        this.tv_coin = tv_coin;
    }


    public RewardDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RewardDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LinearLayout llCancel = findViewById(R.id.llCancel);
        LinearLayout llWatchAds = findViewById(R.id.llWatchAds);
        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        llWatchAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO show rewarded ads and give them reward coins*/
                dismiss();
                showRewardedAds(activity, tv_coin);
            }
        });
    }
}
