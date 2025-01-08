package com.thorient.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thorient.quiz.R;
import com.thorient.quiz.database.AppDatabase;

public class Result_Activity extends AppCompatActivity {
    private View include;
    private ImageView menu_icn;
    private LinearLayout result;
    private LinearLayout home;
    private LinearLayout exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result_Activity.this,MyResultActivity.class));
                Toast.makeText(Result_Activity.this, "Go to result activity", Toast.LENGTH_SHORT).show();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    AppDatabase.getInstance(Result_Activity.this).clearAllTables();
                }).start();
                startActivity(new Intent(Result_Activity.this, MainActivity.class));
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    AppDatabase.getInstance(Result_Activity.this).clearAllTables();
                }).start();
                finishAffinity();
            }
        });
    }

    private void initView() {
        include = findViewById(R.id.include);
        menu_icn = include.findViewById(R.id.menu_icn);
        result = findViewById(R.id.result);
        home = findViewById(R.id.home);
        exit = findViewById(R.id.exit);

    }
}