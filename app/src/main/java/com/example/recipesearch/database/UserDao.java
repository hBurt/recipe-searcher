package com.example.recipesearch.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertDetails(User data);

    @Query("select * from User")
    List<User> getDetails();

    @Query("select * from User where email like :email")
    User getUserByEmail(String email);

    @Query("delete from User")
    void deleteAllData();

}

