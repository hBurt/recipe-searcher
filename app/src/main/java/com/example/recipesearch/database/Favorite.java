package com.example.recipesearch.database;

public class Favorite {
    private int id, rating;

    public Favorite(int id, int rating) {
        setId(id);
        setRating(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
