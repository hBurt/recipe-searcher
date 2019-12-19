package com.example.recipesearch.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertDetails(User data);

    @Query("select * from User")
    LiveData<List<User>> getDetails();

    @Query("delete from User")
    void deleteAllData();

}

