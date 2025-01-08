package com.thorient.quiz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(QuestionDatabase question);

    @Update
    void update(QuestionDatabase question);

    @Query("SELECT * FROM questions")
    List<QuestionDatabase> getAllQuestions();

    @Query("SELECT * FROM questions WHERE id = :id")
    QuestionDatabase getQuestionById(int id);

    @Query("DELETE FROM questions")
    void deleteAllQuestions();

    @Query("SELECT * FROM questions WHERE id = :id")
    boolean isQuestionExists(int id);
}
