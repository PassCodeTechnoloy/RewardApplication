package com.thorient.quiz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thorient.quiz.R;

public class Contact_Activity extends AppCompatActivity {
    View include;

    ImageView menu_icn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        include = findViewById(R.id.include);

        menu_icn = include.findViewById(R.id.menu_icn);

        menu_icn.setImageDrawable(getResources().getDrawable(R.drawable.back_icn));

        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}