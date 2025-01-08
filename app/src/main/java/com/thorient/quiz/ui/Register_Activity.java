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
import com.thorient.quiz.interfaces.ApiClient;
import com.thorient.quiz.interfaces.ApiService;
import com.thorient.quiz.model.RegistrationResponse;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {
    private View include;
    private ImageView menu_icn;
    private EditText name_text, email_text, password_text, c_password_text;
    private LinearLayout Register_click;
    private RewardViewModel rewardViewModel;
    private TemplateView template;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
        intView();
        LoadNativeAds.loadNativeAds(this,template,shimmerFrameLayout);
        menu_icn.setImageDrawable(getResources().getDrawable(R.drawable.back_icn));
        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Register_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_text.getText().toString().trim();
                String email = email_text.getText().toString().trim();
                String c_password = c_password_text.getText().toString().trim();
                String password = password_text.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || c_password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser_(name, email, c_password, password);
                }
            }
        });
        findViewById(R.id.login_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register_Activity.this, Login_Activity.class));
            }
        });
    }

    private void registerUser_(String name, String email, String cPassword, String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("fullname", name);
        map.put("email", email);
        map.put("fcm_token", cPassword);
        map.put("password", password);
        map.put("coin", 100);
        String json = new Gson().toJson(map);
        rewardViewModel.getRegistrationResponseLiveData(json).observe(this, new Observer<RegistrationResponse>() {
            @Override
            public void onChanged(RegistrationResponse registrationResponse) {
                if (registrationResponse != null && registrationResponse.isSuccess()) {
                    Toast.makeText(Register_Activity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Register_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intView() {
        include = findViewById(R.id.include);
        name_text = findViewById(R.id.name_text);
        email_text = findViewById(R.id.email_text);
        password_text = findViewById(R.id.password_text);
        c_password_text = findViewById(R.id.c_password_text);
        Register_click = findViewById(R.id.Register_click);
        menu_icn = include.findViewById(R.id.menu_icn);
        template = findViewById(R.id.my_template);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);

    }
}