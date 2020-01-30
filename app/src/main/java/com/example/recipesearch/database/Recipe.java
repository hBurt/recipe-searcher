package com.example.recipesearch.database;

import java.io.Serializable;

public class Recipe implements Serializable {

    private int id;
    private String title;
    private int readyInMinutes;
    private String imageURL;

    public Recipe(){
        setId(0);
        setTitle("");
        setReadyInMiniutes(0);
        setImageURL("");
    }

    public Recipe(int id, String title, int readyInMinutes, String imageURL) {
        setId(id);
        setTitle(title);
        setReadyInMiniutes(readyInMinutes);
        setImageURL(imageURL);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMiniutes() {
        return readyInMinutes;
    }

    public void setReadyInMiniutes(int readyInMiniutes) {
        this.readyInMinutes = readyInMiniutes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
