package com.thorient.quiz.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.thorient.quiz.R;
import com.thorient.quiz.Repository.RewardViewModel;
import com.thorient.quiz.adpter.QuizListAdapter;
import com.thorient.quiz.model.Quiz;
import com.thorient.quiz.model.QuizResponse;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {
    private int categoryId;
    private RecyclerView quizRecyclerView;
    private List<Quiz> quizzes;
    private QuizListAdapter quizListAdapter;
    private RewardViewModel rewardViewModel;
    private TextView tv_coin;

    public QuizFragment(int id, TextView tv_coin) {
        this.categoryId = id;
        this.tv_coin = tv_coin;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_item, container, false);
        intiView(view);
        quizRecyclerView.setAdapter(quizListAdapter);
        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
        loadQuizzes(categoryId);
        return view;
    }


    private void loadQuizzes(int categoryId) {
        rewardViewModel.getQuizLiveData().observe(getViewLifecycleOwner(), new Observer<QuizResponse>() {
            @Override
            public void onChanged(QuizResponse response) {
                if (response != null) {
                    List<Quiz> quizzes = response.getData();
                    List<Quiz> categoryQuizzes = new ArrayList<>();
                    for (Quiz quiz : quizzes) {
                        if (quiz.getCategoryId() == categoryId) {
                            categoryQuizzes.add(quiz);
                        }
                    }
                    quizListAdapter.setQuizzes(categoryQuizzes);
                }
            }
        });
    }

    private void intiView(View view) {
        quizzes = new ArrayList<>();
        quizListAdapter = new QuizListAdapter(getActivity(), quizzes,tv_coin);
        quizRecyclerView = view.findViewById(R.id.quizRecyclerView);
    }
}
