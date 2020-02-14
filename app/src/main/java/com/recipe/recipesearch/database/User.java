package com.recipe.recipesearch.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "User")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "Email")
    private String email;

    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "Favorites")
    private ArrayList<Favorite> favorites = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Favorite> favorites) {
        this.favorites = favorites;
    }
}

