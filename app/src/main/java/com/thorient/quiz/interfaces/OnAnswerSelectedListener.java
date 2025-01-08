package com.thorient.quiz.interfaces;

public interface OnAnswerSelectedListener {
    void onAnswerSelected(boolean isCorrect);
//    void showRewardDialog();
    void insertData(String question, String optionA, String optionB, String optionC, String optionD, int color1, int color2, int color3, int color4, String correctAnswer, String userSelectAnswer);
    void updateDataIntrface(String question, String optionA, String optionB, String optionC, String optionD, int color1, int color2, int color3, int color4, String correctAnswer, String userSelectAnswer);
}

