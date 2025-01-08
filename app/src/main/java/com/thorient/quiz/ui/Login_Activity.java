package com.thorient.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.thorient.quiz.R;
import com.thorient.quiz.Repository.RewardViewModel;
import com.thorient.quiz.ads.native_ads.LoadNativeAds;
import com.thorient.quiz.ads.native_ads.TemplateView;
import com.thorient.quiz.model.LoginResponse;

import java.util.HashMap;

public class Login_Activity extends AppCompatActivity {
    private View include;
    private ImageView menu_icn;
    private EditText email_text, password_text;
    private LinearLayout Login_click;
    private RewardViewModel rewardViewModel;
    private TemplateView templateView;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        LoadNativeAds.loadNativeAds(this,templateView,shimmerFrameLayout);
        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
        Login_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_text.getText().toString().trim();
                String password = password_text.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser_(email, password);
                }
            }
        });
        menu_icn = include.findViewById(R.id.menu_icn);
        menu_icn.setImageDrawable(getResources().getDrawable(R.drawable.back_icn));
        menu_icn.setOnClickListener(view -> finish());
        findViewById(R.id.register_now).setOnClickListener(view -> startActivity(new Intent(Login_Activity.this, Register_Activity.class)));
    }

    private void loginUser_(String email, String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("password", password);
        map.put("email", email);
        String json = new Gson().toJson(map);
        rewardViewModel.loginUser(json).observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    Toast.makeText(Login_Activity.this, "" + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login_Activity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        include = findViewById(R.id.include);
        email_text = findViewById(R.id.email_text);
        password_text = findViewById(R.id.password_text);
        Login_click = findViewById(R.id.Login_click);
        templateView = findViewById(R.id.my_template);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);

    }

}