package com.example.recipesearch.database.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recipesearch.database.User;
import com.example.recipesearch.database.repository.LoginLocalRepository;

import java.util.List;

public class LoginLocalViewModel extends AndroidViewModel {

    private LoginLocalRepository repository;
    private LiveData<List<User>> getAllUsers;

    public LoginLocalViewModel(@NonNull Application application) {
        super(application);

        repository = new LoginLocalRepository(application);
        getAllUsers = repository.getAllUsers();
    }

    public void insert(User user){
        repository.addUser(user);
    }

    public LiveData<List<User>> getGetAllUsers() {
        return getAllUsers;
    }
}
