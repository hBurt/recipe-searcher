package com.example.recipesearch;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.recipesearch.database.LocalLoginDatabase;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {

    private CharSequence message;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalLoginDatabase database = Room.databaseBuilder(this, LocalLoginDatabase.class, "LOCAL_LOGIN_DATABASE")
                .allowMainThreadQueries().build();

        databaseHelper = new DatabaseHelper(database, this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        // keep layout when keyboard is shown
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

    }

    public void setMessage(CharSequence s){
        message = s;
    }

    public CharSequence getMessage(){
        return message;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}
