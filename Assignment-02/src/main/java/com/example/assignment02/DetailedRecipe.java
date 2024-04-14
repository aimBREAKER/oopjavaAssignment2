package com.example.assignment02;

public class DetailedRecipe {
    private String title;
    private String readyInMinutes;
    private String image;
    private double healthScore;
    public DetailedRecipe() {
    }
    public DetailedRecipe(String title,String readyInMinutes, String image, double healthScore) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.image = image;
        this.healthScore = healthScore;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setId(String readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(double healthScore) {
        this.healthScore = healthScore;
    }
}
