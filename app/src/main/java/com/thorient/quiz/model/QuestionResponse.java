package com.thorient.quiz.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QuestionResponse {

    @SerializedName("data")
    private List<Question> data;

    public List<Question> getData() {
        return data;
    }

    public void setData(List<Question> data) {
        this.data = data;
    }
}

