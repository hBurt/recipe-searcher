package com.example.recipesearch.database;

import java.io.Serializable;

public class Recipe implements Serializable {

    private int id;
    private String title, imageURL, baseImageURI, ingredients, directions;
    private int readyInMinutes;

    public Recipe(){
        setId(0);
        setTitle("");
        setReadyInMiniutes(0);
        setImageURL("");
        setIngredients("");
        setDirections("");
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getBaseImageURI() {
        return baseImageURI;
    }

    public void setBaseImageURI(String baseImageURI) {
        this.baseImageURI = baseImageURI;
    }
}
