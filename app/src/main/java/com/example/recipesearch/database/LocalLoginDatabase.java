package com.example.recipesearch.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class LocalLoginDatabase extends RoomDatabase {

    public abstract UserDao loginDoa();

    private static LocalLoginDatabase INSTANCE;

    public static LocalLoginDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (LocalLoginDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context, LocalLoginDatabase.class, "LOCAL_LOGIN_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
