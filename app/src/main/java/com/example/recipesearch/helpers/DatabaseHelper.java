package com.example.recipesearch.helpers;

import com.example.recipesearch.database.LocalLoginDatabase;
import com.example.recipesearch.database.User;

import java.util.List;

public class DatabaseHelper {

    private LocalLoginDatabase database;
    private User currentUser;

    public DatabaseHelper(LocalLoginDatabase database){
        setDatabase(database);
    }

    public LocalLoginDatabase getDatabase() {
        return database;
    }

    public void setDatabase(LocalLoginDatabase database) {
        this.database = database;
    }

    public List<User> returnUsers(){
        return getDatabase().getUserDao().getDetails();
    }

    public void addUser(User user){
        getDatabase().getUserDao().insertDetails(user);
    }

    public void updateUser(User user){
        getDatabase().getUserDao().updateDetails(user);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
