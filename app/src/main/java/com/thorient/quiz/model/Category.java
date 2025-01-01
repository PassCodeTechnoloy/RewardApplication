package com.thorient.quiz.model;

public class Category {
    private int id;
    private String name;
    private String thumbnail;
    private boolean isActive;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}



