package com.thorient.quiz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thorient.quiz.R;

public class Login_Activity extends AppCompatActivity {


    View include;

    ImageView menu_icn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        include = findViewById(R.id.include);

        menu_icn = include.findViewById(R.id.menu_icn);

        menu_icn.setImageDrawable(getResources().getDrawable(R.drawable.back_icn));

        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        findViewById(R.id.register_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this,Register_Activity.class));
            }
        });
    }
}