package com.thorient.quiz.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thorient.quiz.R;
import com.thorient.quiz.database.QuestionDao;
import com.thorient.quiz.database.QuestionDatabase;

import java.util.List;

public class MyResultAdapter extends RecyclerView.Adapter<MyResultAdapter.ViewHolder> {
    private List<QuestionDatabase> questions;

    QuestionDao questionDao;

    public MyResultAdapter(List<QuestionDatabase> questions, QuestionDao questionDao) {
        this.questions = questions;
        this.questionDao = questionDao;
    }

    @NonNull
    @Override
    public MyResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myresult, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyResultAdapter.ViewHolder holder, int position) {
        QuestionDatabase question = questions.get(position);
        holder.tvQuestion.setText(question.getQuestionText());
        holder.tvOption1.setText(question.getOption1());
        holder.tvOption2.setText(question.getOption2());
        holder.tvOption3.setText(question.getOption3());
        holder.tvOption4.setText(question.getOption4());
        holder.tvQuestionNo.setText(String.valueOf(position + 1));
        try {
            holder.tvOption1.setTextColor(question.getColor1());
            holder.tvOption2.setTextColor(question.getColor2());
            holder.tvOption3.setTextColor(question.getColor3());
            holder.tvOption4.setTextColor(question.getColor4());
        } catch (Exception exception) {

        }
    }

    @Override
    public int getItemCount() {
        if (questions != null) {
            return questions.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvQuestion, tvOption1, tvOption2, tvOption3, tvOption4, tvQuestionNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvOption1 = itemView.findViewById(R.id.tvOption1);
            tvOption2 = itemView.findViewById(R.id.tvOption2);
            tvOption3 = itemView.findViewById(R.id.tvOption3);
            tvOption4 = itemView.findViewById(R.id.tvOption4);
            tvQuestionNo = itemView.findViewById(R.id.tvQuestionNo);
        }
    }
}
