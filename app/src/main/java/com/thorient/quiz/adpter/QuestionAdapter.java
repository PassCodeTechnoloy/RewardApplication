package com.thorient.quiz.adpter;

import static com.thorient.quiz.prefrence.SharedPrefsUtil.getValue;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thorient.quiz.R;
import com.thorient.quiz.database.QuestionDao;
import com.thorient.quiz.database.QuestionDatabase;
import com.thorient.quiz.interfaces.OnAnswerSelectedListener;
import com.thorient.quiz.model.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private Context context;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private OnAnswerSelectedListener onAnswerSelectedListener;
    private QuestionDao questionDao;
    private List<QuestionDatabase> questionDatabases;


    public QuestionAdapter(Context context, List<Question> questions, List<QuestionDatabase> questionDao, OnAnswerSelectedListener listener) {
        this.context = context;
        this.questions = questions;
        this.onAnswerSelectedListener = listener;
        this.questionDatabases = questionDao;


    }

    public void setCurrentQuestionIndex(int index) {
        this.currentQuestionIndex = index;
        notifyDataSetChanged();
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

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedAnswer = ((TextView) v).getText().toString();
                boolean isCorrect = selectedAnswer.equals(question.getCorrectAnswer());

//                if (getValue(context, "coin", 0) >= 200) {
                    if (onAnswerSelectedListener != null) {
                        onAnswerSelectedListener.onAnswerSelected(isCorrect);
                    }
                    if (isCorrect) {
                        v.setBackground(context.getResources().getDrawable(R.drawable.true_ansbg));
                        ((TextView) v).setTextColor(context.getColor(R.color.select_text_color));
                    } else {
                        v.setBackground(context.getResources().getDrawable(R.drawable.false_ansbg));
                        ((TextView) v).setTextColor(context.getColor(R.color.white));
                    }
                    if (isCorrect) {
                        if (position == 0) {
                            onAnswerSelectedListener.insertData(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), context.getColor(R.color.select_text_color), 0, 0, 0, question.getCorrectAnswer(), question.getCorrectAnswer());
                        } else if (position == 1) {
                            onAnswerSelectedListener.insertData(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), 0, context.getColor(R.color.select_text_color), 0, 0, question.getCorrectAnswer(), question.getCorrectAnswer());

                        } else if (position == 2) {
                            onAnswerSelectedListener.insertData(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), 0, 0, context.getColor(R.color.select_text_color), 0, question.getCorrectAnswer(), question.getCorrectAnswer());

                        } else if (position == 3) {
                            onAnswerSelectedListener.insertData(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), 0, 0, 0, context.getColor(R.color.select_text_color), question.getCorrectAnswer(), question.getCorrectAnswer());

                        }
                    }
                    try {
                        if (!isCorrect) {
                            if (position == 0) {
                                onAnswerSelectedListener.updateDataIntrface(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), context.getColor(R.color.red_color), questionDatabases.get(0).getColor1(), questionDatabases.get(0).getColor1(), questionDatabases.get(0).getColor1(), question.getCorrectAnswer(), question.getCorrectAnswer());

                            } else if (position == 1) {
                                onAnswerSelectedListener.updateDataIntrface(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), questionDatabases.get(1).getColor2(), context.getColor(R.color.red_color), questionDatabases.get(1).getColor2(), questionDatabases.get(1).getColor2(), question.getCorrectAnswer(), question.getCorrectAnswer());

                            } else if (position == 2) {
                                onAnswerSelectedListener.updateDataIntrface(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), questionDatabases.get(2).getColor3(), questionDatabases.get(2).getColor3(), context.getColor(R.color.red_color), questionDatabases.get(2).getColor3(), question.getCorrectAnswer(), question.getCorrectAnswer());

                            } else if (position == 3) {
                                onAnswerSelectedListener.updateDataIntrface(question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), questionDatabases.get(3).getColor4(), questionDatabases.get(3).getColor4(), questionDatabases.get(3).getColor4(), context.getColor(R.color.red_color), question.getCorrectAnswer(), question.getCorrectAnswer());

                            }
                        }
                    } catch (Exception exception) {
                        Log.e("TEST", "erroe: "+exception.getMessage() );
                    }


//                }
//                else {
////                    onAnswerSelectedListener.showRewardDialog();
//                }
            }
        };

        holder.optionA.setOnClickListener(listener);
        holder.optionB.setOnClickListener(listener);
        holder.optionC.setOnClickListener(listener);
        holder.optionD.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return 1;
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

