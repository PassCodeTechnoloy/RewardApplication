package com.thorient.quiz.model;

import java.util.List;

public class CategoryResponse {
    private List<Category> data;
    private String message;
    private int status;

    public List<Category> getData() { return data; }
    public void setData(List<Category> data) { this.data = data; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}

