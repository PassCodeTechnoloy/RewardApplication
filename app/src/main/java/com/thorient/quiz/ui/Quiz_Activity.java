package com.thorient.quiz.ui;

import static com.thorient.quiz.prefrence.SharedPrefsUtil.decreaseValue;
import static com.thorient.quiz.prefrence.SharedPrefsUtil.getValue;
import static com.thorient.quiz.prefrence.SharedPrefsUtil.increaseValue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.MobileAds;
import com.thorient.quiz.R;
import com.thorient.quiz.Repository.RewardViewModel;
import com.thorient.quiz.adpter.QuestionAdapter;
import com.thorient.quiz.ads.native_ads.LoadNativeAds;
import com.thorient.quiz.ads.native_ads.TemplateView;
import com.thorient.quiz.database.AppDatabase;
import com.thorient.quiz.database.QuestionDao;
import com.thorient.quiz.database.QuestionDatabase;
import com.thorient.quiz.dialog.RewardDialog;
import com.thorient.quiz.interfaces.OnAnswerSelectedListener;
import com.thorient.quiz.model.Question;
import com.thorient.quiz.model.QuestionResponse;

import java.util.ArrayList;
import java.util.List;

public class Quiz_Activity extends AppCompatActivity {
    private View include;
    private ImageView menu_icn;
    private int category_id;
    private QuestionAdapter questionAdapter;
    private RecyclerView questionsRecyclerView;
    //    private List<Question> categoryQuizzes;
    private int currentQuestionIndex = 0;
    private ProgressBar progressBar;
    private TextView questionNumberTextView;
    private RewardViewModel rewardViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;
    private TemplateView template;
    private TextView tv_coin;
    private QuestionDao questionDao;
    private List<QuestionDatabase> questionDatabases;
    public int con_value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
        con_value = getIntent().getIntExtra("coin_value",0);
        new Thread(() -> {
            questionDao = AppDatabase.getInstance(Quiz_Activity.this).questionDao();
            questionDatabases = questionDao.getAllQuestions();
        }).start();
        intiView();
        category_id = getIntent().getIntExtra("category_id", 0);
        loadQuestions();
        menu_icn.setImageDrawable(getResources().getDrawable(R.drawable.back_icn));
        menu_icn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LoadNativeAds.loadNativeAds(this, template, shimmerFrameLayout);
        tv_coin.setText(String.valueOf(getValue(this, "coin", 0)));


    }

    private void loadQuestions() {
        rewardViewModel.getQuestionResponseLiveData().observe(this, new Observer<QuestionResponse>() {
            @Override
            public void onChanged(QuestionResponse questionResponse) {
                if (questionResponse != null) {
                    List<Question> quizzes = questionResponse.getData();
                    List<Question> categoryQuizzes = new ArrayList<>();
                    for (Question quiz : quizzes) {
                        if (quiz.getQuizId() == category_id) {
                            categoryQuizzes.add(quiz);
                        }
                    }
                    if (!categoryQuizzes.isEmpty()) {
                        progressBar.setMax(categoryQuizzes.size());
                        questionAdapter = new QuestionAdapter(Quiz_Activity.this, categoryQuizzes, questionDatabases, new OnAnswerSelectedListener() {
                            @Override
                            public void onAnswerSelected(boolean isCorrect) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isCorrect) {
                                            increaseValue(Quiz_Activity.this, "coin", con_value);
                                            tv_coin.setText(String.valueOf(getValue(Quiz_Activity.this, "coin", 0)));
                                        } else {
                                            decreaseValue(Quiz_Activity.this, "coin", con_value);
                                            tv_coin.setText(String.valueOf(getValue(Quiz_Activity.this, "coin", 0)));
                                        }
                                        currentQuestionIndex++;
                                        if (currentQuestionIndex < categoryQuizzes.size()) {
                                            progressBar.setProgress(currentQuestionIndex);
                                            questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + "/" + categoryQuizzes.size());
                                            questionAdapter.setCurrentQuestionIndex(currentQuestionIndex);
                                        } else {
                                            try {
                                                Intent intent = new Intent(Quiz_Activity.this, Result_Activity.class);
                                                startActivity(intent);
//                                                startActivity(new Intent(Quiz_Activity.this, Result_Activity.class));
//                                            finish();
                                                Toast.makeText(Quiz_Activity.this, "Quiz Completed!", Toast.LENGTH_SHORT).show();
                                            } catch (Exception exception) {
                                                Log.d("", "Error: " + exception.getMessage());
                                            }
                                        }
                                    }
                                }, 400);
                            }

//                            @Override
//                            public void showRewardDialog() {
//                                RewardDialog dialog = new RewardDialog(Quiz_Activity.this, Quiz_Activity.this, tv_coin);
//                                dialog.show();
//                            }

                            @Override
                            public void insertData(String question, String optionA, String optionB, String optionC, String optionD, int color1, int color2, int color3, int color4, String correctAnswer, String userSelectAnswer) {
                                new Thread(() -> {
                                    QuestionDatabase question1 = new QuestionDatabase(
                                            question,
                                            optionA,
                                            optionB,
                                            optionC,
                                            optionD,
                                            color1,
                                            color2,
                                            color3,
                                            color4,
                                            correctAnswer,
                                            userSelectAnswer
                                    );
                                    AppDatabase.getInstance(Quiz_Activity.this).questionDao().insert(question1);
                                }).start();
                            }

                            @Override
                            public void updateDataIntrface(String question, String optionA, String optionB, String optionC, String optionD, int color1, int color2, int color3, int color4, String correctAnswer, String userSelectAnswer) {

                                new Thread(() -> {
                                    QuestionDatabase question1 = new QuestionDatabase(
                                            question,
                                            optionA,
                                            optionB,
                                            optionC,
                                            optionD,
                                            color1,
                                            color2,
                                            color3,
                                            color4,
                                            correctAnswer,
                                            userSelectAnswer
                                    );
                                    AppDatabase.getInstance(Quiz_Activity.this).questionDao().update(question1);
                                }).start();
                            }
                        });
                        questionsRecyclerView.setAdapter(questionAdapter);
                    }
                }
            }
        });
    }

    private void intiView() {
        MobileAds.initialize(this);
        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        include = findViewById(R.id.include);
        menu_icn = include.findViewById(R.id.menu_icn);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        template = findViewById(R.id.my_template);
        tv_coin = include.findViewById(R.id.tv_coin);
    }
}