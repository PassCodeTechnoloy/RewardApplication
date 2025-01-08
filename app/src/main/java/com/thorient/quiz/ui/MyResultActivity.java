package com.thorient.quiz.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.thorient.quiz.R;
import com.thorient.quiz.adpter.MyResultAdapter;
import com.thorient.quiz.database.AppDatabase;
import com.thorient.quiz.database.QuestionDao;
import com.thorient.quiz.database.QuestionDatabase;
import com.thorient.quiz.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyResultActivity extends AppCompatActivity {
    private RecyclerView rvMyResult;
    private ArrayList<String> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_result_activity);
        initView();
        new Thread(() -> {
            QuestionDao questionDao = AppDatabase.getInstance(this).questionDao();
            List<QuestionDatabase> questions = questionDao.getAllQuestions();
            rvMyResult.setAdapter(new MyResultAdapter(questions,questionDao));
        }).start();
    }
    private void initView() {
        rvMyResult = findViewById(R.id.rvMyResult);
    }
}
