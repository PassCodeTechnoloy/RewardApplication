package com.thorient.quiz.model;

import java.util.List;

public class QuizResponse {
    private List<Quiz> data;
    private String message;
    private int status;

    // Getters and Setters
    public List<Quiz> getData() { return data; }
    public void setData(List<Quiz> data) { this.data = data; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}

