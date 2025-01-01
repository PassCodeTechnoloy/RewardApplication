package com.thorient.quiz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.thorient.quiz.R;
import com.thorient.quiz.adpter.QuestionAdapter;
import com.thorient.quiz.interfaces.ApiClient;
import com.thorient.quiz.interfaces.ApiService;
import com.thorient.quiz.interfaces.OnAnswerSelectedListener;
import com.thorient.quiz.model.Question;
import com.thorient.quiz.model.QuestionResponse;
import com.thorient.quiz.model.Quiz;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quiz_Activity extends AppCompatActivity {
    View include;

    ImageView menu_icn;

    int category_id;

    private QuestionAdapter questionAdapter;
    private RecyclerView questionsRecyclerView;
    private List<Question> categoryQuizzes;
    private int currentQuestionIndex = 0;

    private ProgressBar progressBar;
    private TextView questionNumberTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);


        include = findViewById(R.id.include);

        menu_icn = include.findViewById(R.id.menu_icn);

        menu_icn.setImageDrawable(getResources().getDrawable(R.drawable.back_icn));

        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        category_id = getIntent().getIntExtra("category_id", 0);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);


        apiService.getQuestions(true).enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<Question> quizzes = response.body().getData();
                    List<Question> categoryQuizzes = new ArrayList<>();
                    for (Question quiz : quizzes) {
                        if (quiz.getQuizId() == category_id) {
                            categoryQuizzes.add(quiz);
                        }
                        if (!categoryQuizzes.isEmpty()) {
                            progressBar.setMax(categoryQuizzes.size());
                            questionAdapter = new QuestionAdapter(Quiz_Activity.this, categoryQuizzes, new OnAnswerSelectedListener() {
                                @Override
                                public void onAnswerSelected(boolean isCorrect) {
                                    new Handler().postDelayed(() -> {
                                        currentQuestionIndex++;
                                        if (currentQuestionIndex < categoryQuizzes.size()) {
                                            progressBar.setProgress(currentQuestionIndex);
                                            questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + "/" + categoryQuizzes.size());
                                            questionAdapter.setCurrentQuestionIndex(currentQuestionIndex);
                                        } else {
                                            Toast.makeText(Quiz_Activity.this, "Quiz Completed!", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 500);
                                }
                            });
                            questionsRecyclerView.setAdapter(questionAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                Log.d("checkactivity", "onFailure====" + call);
                // Handle failure
            }
        });
    }
}