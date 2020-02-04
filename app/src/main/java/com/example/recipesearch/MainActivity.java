package com.example.recipesearch;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.recipesearch.helpers.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private CharSequence message;
    private DatabaseHelper databaseHelper;
    private BottomNavigationView navView;
    private ConstraintLayout constraintLayout;
    ConstraintLayout.LayoutParams newLayoutParams;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.rebuildDatabase();

        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        autoLogin("test2@test.com", "1234");


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

    public void setBottomNavigationVisibility(int viewID){
        navView.setVisibility(viewID);
    }

    private void autoLogin(String email, String pass){

        //if(databaseHelper.isLoginStateSaved()){
        //    System.out.println("Saved state login");
        //    databaseHelper.login(databaseHelper.getSharedPrefEmail(), databaseHelper.getSharedPrefPass());
        //} else {
        //    System.out.println("input login");
            databaseHelper.login(email, pass);
        //}
    }

}
