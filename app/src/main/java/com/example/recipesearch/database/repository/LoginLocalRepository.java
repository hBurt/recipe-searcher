package com.example.recipesearch.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.recipesearch.database.LocalLoginDatabase;
import com.example.recipesearch.database.User;
import com.example.recipesearch.database.UserDao;

import java.util.List;

public class LoginLocalRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public LoginLocalRepository(Application application){
        LocalLoginDatabase db = LocalLoginDatabase.getDatabase(application);
        userDao = db.loginDoa();
        allUsers = userDao.getDetails();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public void addUser(User data){
        new LoginInsertion(userDao).execute(data);
    }

    private static class LoginInsertion extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private LoginInsertion(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {

            userDao.deleteAllData();
            userDao.insertDetails(users[0]);
            return null;
        }
    }
}
