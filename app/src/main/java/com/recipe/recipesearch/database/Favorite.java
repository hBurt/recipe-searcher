package com.recipe.recipesearch.database;

import java.io.Serializable;

public class Favorite implements Serializable {

    private int rating;

    private Recipe recipe;

    public Favorite(Recipe recipe) {
        setRating(0);
        setRecipe(recipe);
    }

    public Favorite(int rating, Recipe recipe) {
        setRating(rating);
        setRecipe(recipe);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}