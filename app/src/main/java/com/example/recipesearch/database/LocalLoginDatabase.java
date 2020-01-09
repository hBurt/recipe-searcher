package com.example.recipesearch.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class LocalLoginDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();
}
