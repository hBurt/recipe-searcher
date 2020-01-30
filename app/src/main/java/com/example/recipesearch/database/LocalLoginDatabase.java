package com.example.recipesearch.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class}, version = 2, exportSchema = false)
@TypeConverters({FavoriteConverter.class})
public abstract class LocalLoginDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();


}
