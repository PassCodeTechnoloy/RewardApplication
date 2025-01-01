package com.thorient.quiz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.thorient.quiz.R;
import com.thorient.quiz.adpter.QuizPagerAdapter;
import com.thorient.quiz.interfaces.ApiClient;
import com.thorient.quiz.interfaces.ApiService;
import com.thorient.quiz.model.Category;
import com.thorient.quiz.model.CategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    View include;
    ImageView menu_icn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        include = findViewById(R.id.include);

        menu_icn = include.findViewById(R.id.menu_icn);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);


        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getCategories(true).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body().getData();
                    if (categories != null && !categories.isEmpty()) {
                        Log.d("MainActivity", "Categories fetched: " + categories.size());
                        QuizPagerAdapter quizPagerAdapter = new QuizPagerAdapter(MainActivity.this, categories);
                        viewPager2.setAdapter(quizPagerAdapter);

                        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
                            tab.setText(categories.get(position).getName());
                        }).attach();
                    } else {
                        Log.d("MainActivity", "No categories found!");
                    }
                } else {
                    Log.d("MainActivity", "Failed to fetch categories: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t.getMessage());
                // Handle failure
            }
        });



        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.main_relativ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.GONE);
            }
        }); findViewById(R.id.rendome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.GONE);
            }
        });findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this,Contact_Activity.class));
            }
        });findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.GONE);
            }
        });findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.main_relativ).setVisibility(View.GONE);
                startActivity(new Intent(MainActivity.this,Login_Activity.class));

            }
        });
    }
}