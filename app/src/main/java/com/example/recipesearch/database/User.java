package com.example.recipesearch.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "Email")
    private String email;

    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "Favorites")
    private ArrayList<Favorite> favorites;

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

