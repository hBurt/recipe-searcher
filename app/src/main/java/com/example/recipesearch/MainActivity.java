package com.example.recipesearch;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.recipesearch.database.LocalLoginDatabase;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private CharSequence message;
    private DatabaseHelper databaseHelper;
    private BottomNavigationView navView;

    @Override
    protected void onResume() {
        super.onResume();
        setBottomNavigationVisibility();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalLoginDatabase database = Room.databaseBuilder(this, LocalLoginDatabase.class, "LOCAL_LOGIN_DATABASE")
                .allowMainThreadQueries().build();

        databaseHelper = new DatabaseHelper(database, this);

        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        autoLogin("test1@test.com", "1234");

        setBottomNavigationVisibility();
        
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

    private void setBottomNavigationVisibility(){
        navView.setVisibility(databaseHelper.loginCheck() ? View.VISIBLE : View.INVISIBLE);
    }

    public void setBottomNavigationVisibility(int viewID){
        navView.setVisibility(viewID);
    }

    private void autoLogin(String email, String pass){
        databaseHelper.login(email, pass);
    }

}
