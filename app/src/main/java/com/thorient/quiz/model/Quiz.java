package com.thorient.quiz.model;

public class Quiz {
    private int id;
    private int category_id;
    private String title;
    private String thumbnail;
    private int coin;
    private boolean isActive;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCategoryId() { return category_id; }
    public void setCategoryId(int categoryId) { this.category_id = categoryId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public int getCoin() { return coin; }
    public void setCoin(int coin) { this.coin = coin; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
