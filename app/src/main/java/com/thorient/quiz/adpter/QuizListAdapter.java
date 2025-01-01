package com.thorient.quiz.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.thorient.quiz.R;
import com.thorient.quiz.model.Quiz;
import com.thorient.quiz.ui.Quiz_Activity;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuizViewHolder> {
    private Context context;
    private List<Quiz> quizzes;

    public QuizListAdapter(Context context, List<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each quiz
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = quizzes.get(position);

        holder.quizTitleTextView.setText(quiz.getTitle());
        holder.coinTextView.setText("" + quiz.getCoin());

        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(context, Quiz_Activity.class).putExtra("category_id",quiz.getId()));
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
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
