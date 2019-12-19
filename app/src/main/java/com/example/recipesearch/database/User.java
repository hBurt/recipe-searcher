package com.example.recipesearch.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Vector;

@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "email")
    public String email;

    //Use char[], strings are immutable and wont be cleaned up by the garbage collector
    @ColumnInfo(name = "password")
    public String password;

    //@ColumnInfo(name = "favorites")
    //public Vector<Integer> favorites;
}

