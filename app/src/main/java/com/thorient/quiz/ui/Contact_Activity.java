package com.thorient.quiz.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.thorient.quiz.R;
import com.thorient.quiz.Repository.RewardViewModel;
import com.thorient.quiz.interfaces.ApiClient;
import com.thorient.quiz.interfaces.ApiService;
import com.thorient.quiz.model.ContactResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contact_Activity extends AppCompatActivity {
    private View include;
    private ImageView menu_icn;
    private EditText nameEditText, emailEditText, messageEditText;
    private LinearLayout submitButton;
    private RewardViewModel rewardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
        intView();

        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String message = messageEditText.getText().toString().trim();
                if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                    Toast.makeText(Contact_Activity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    submitContactForm_(name, email, message);
                }
            }
        });
    }

    private void intView() {
        include = findViewById(R.id.include);
        menu_icn = include.findViewById(R.id.menu_icn);
        menu_icn.setImageDrawable(getResources().getDrawable(R.drawable.back_icn));
        nameEditText = findViewById(R.id.NameText);
        emailEditText = findViewById(R.id.EmailText);
        messageEditText = findViewById(R.id.MessageText);
        submitButton = findViewById(R.id.submit_btn);

    }

    private void submitContactForm_(String name, String email, String message) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("message", message);
        String json = new Gson().toJson(map);


   /*  rewardViewModel.submitContactForm(json).observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object contactResponse) {

            }
        });*/

      rewardViewModel.submitContactForm(json).observe(this, new Observer<ContactResponse>() {
          @Override
          public void onChanged(ContactResponse o) {
              Toast.makeText(Contact_Activity.this, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();
          }
      });
    }

//    private void submitContactForm(String name, String email, String message) {
//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//        ContactRequest contactRequest = new ContactRequest(name, email, message);
//
//        apiService.submitContactForm(contactRequest).enqueue(new Callback<ContactResponse>() {
//            @Override
//            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    if (response.body().isSuccess()) {
//                        Toast.makeText(Contact_Activity.this, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Contact_Activity.this, "Failed: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(Contact_Activity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ContactResponse> call, Throwable t) {
//                Toast.makeText(Contact_Activity.this, "Request Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}