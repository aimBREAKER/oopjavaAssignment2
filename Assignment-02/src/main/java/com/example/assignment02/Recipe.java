package com.example.assignment02;

public class Recipe {
    private String name;
    private String imageUrl;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
// Constructors, getters, and setters

    public Recipe(String name, String imageUrl, int id) {
        this.setName(name);
        this.setImageUrl(imageUrl);
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String toString()
    {
        return name;
    }
}
