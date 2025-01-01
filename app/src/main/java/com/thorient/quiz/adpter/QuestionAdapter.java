package com.thorient.quiz.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thorient.quiz.R;
import com.thorient.quiz.interfaces.OnAnswerSelectedListener;
import com.thorient.quiz.model.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private Context context;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private OnAnswerSelectedListener onAnswerSelectedListener;

    public QuestionAdapter(Context context, List<Question> questions, OnAnswerSelectedListener listener) {
        this.context = context;
        this.questions = questions;
        this.onAnswerSelectedListener = listener;
    }

    public void setCurrentQuestionIndex(int index) {
        this.currentQuestionIndex = index;
        notifyDataSetChanged(); // Refresh the view to show the next question
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questions.get(currentQuestionIndex);
        holder.questionTextView.setText(question.getQuestion());
        holder.optionA.setText(question.getOptionA());
        holder.optionB.setText(question.getOptionB());
        holder.optionC.setText(question.getOptionC());
        holder.optionD.setText(question.getOptionD());

        resetOptionStyles(holder);
        enableOptions(holder, true);


        // Option click listeners
        View.OnClickListener listener = v -> {
            String selectedAnswer = ((TextView) v).getText().toString();
            boolean isCorrect = selectedAnswer.equals(question.getCorrectAnswer());

            if (isCorrect) {
                v.setBackground(context.getResources().getDrawable(R.drawable.true_ansbg));
                ((TextView) v).setTextColor(context.getColor(R.color.select_text_color));
            } else {
                v.setBackground(context.getResources().getDrawable(R.drawable.false_ansbg));
                ((TextView) v).setTextColor(context.getColor(R.color.white));
            }

            // Notify the activity/fragment
            if (onAnswerSelectedListener != null) {
                onAnswerSelectedListener.onAnswerSelected(isCorrect);
            }
        };

        holder.optionA.setOnClickListener(listener);
        holder.optionB.setOnClickListener(listener);
        holder.optionC.setOnClickListener(listener);
        holder.optionD.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return 1; // Show only one question at a time
    }

    private void resetOptionStyles(QuestionViewHolder holder) {
        holder.optionA.setBackground(context.getResources().getDrawable(R.drawable.card_ansbg));
        holder.optionB.setBackground(context.getResources().getDrawable(R.drawable.card_ansbg));
        holder.optionC.setBackground(context.getResources().getDrawable(R.drawable.card_ansbg));
        holder.optionD.setBackground(context.getResources().getDrawable(R.drawable.card_ansbg));

        holder.optionA.setTextColor(context.getColor(R.color.white));
        holder.optionB.setTextColor(context.getColor(R.color.white));
        holder.optionC.setTextColor(context.getColor(R.color.white));
        holder.optionD.setTextColor(context.getColor(R.color.white));
    }

    private void enableOptions(QuestionViewHolder holder, boolean enable) {
        holder.optionA.setEnabled(enable);
        holder.optionB.setEnabled(enable);
        holder.optionC.setEnabled(enable);
        holder.optionD.setEnabled(enable);
    }


    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView, optionA, optionB, optionC, optionD;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            optionA = itemView.findViewById(R.id.optionA);
            optionB = itemView.findViewById(R.id.optionB);
            optionC = itemView.findViewById(R.id.optionC);
            optionD = itemView.findViewById(R.id.optionD);
        }
    }
}

