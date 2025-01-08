package com.thorient.quiz.adpter;

import static com.thorient.quiz.prefrence.SharedPrefsUtil.getValue;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thorient.quiz.R;
import com.thorient.quiz.dialog.RewardDialog;
import com.thorient.quiz.model.Quiz;
import com.thorient.quiz.ui.Quiz_Activity;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {
    private Activity activity;
    private List<Quiz> quizzes;
    private TextView tv_coin;

    public QuizListAdapter(Activity activity, List<Quiz> quizzes, TextView tv_coin) {
        this.activity = activity;
        this.quizzes = quizzes;
        this.tv_coin = tv_coin;
    }


    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_viewpager, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = quizzes.get(position);
        holder.quizTitleTextView.setText(quiz.getTitle());
        holder.coinTextView.setText("" + quiz.getCoin());
        holder.itemView.setOnClickListener(v -> {
            if (getValue(activity, "coin", 0) < 200) {
                showRewardDialog();
            }else {
                activity.startActivity(new Intent(activity, Quiz_Activity.class).putExtra("category_id", quiz.getId()).putExtra("coin_value", quiz.getCoin()));
            }

        });
    }

    private void showRewardDialog() {
        RewardDialog dialog = new RewardDialog(activity, activity, tv_coin);
        dialog.show();
    }

    @Override
    public int getItemCount() {
        if (quizzes != null) {
            return quizzes.size();
        }
        return 0;
    }

    public void setQuizzes(List<Quiz> categoryQuizzes) {
        this.quizzes = categoryQuizzes;
        notifyDataSetChanged();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        private TextView quizTitleTextView;
        private TextView coinTextView;

        public QuizViewHolder(View itemView) {
            super(itemView);
            quizTitleTextView = itemView.findViewById(R.id.quizTitleTextView);
            coinTextView = itemView.findViewById(R.id.coinTextView);
        }
    }
}
