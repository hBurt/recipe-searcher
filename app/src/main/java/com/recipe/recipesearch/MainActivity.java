package com.recipe.recipesearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.recipe.recipesearch.helpers.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private BottomNavigationView navView;
    private ConstraintLayout constraintLayout;
    ConstraintLayout.LayoutParams newLayoutParams;

    //private boolean loginMessage = false;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {

            requestPermissions(new String[]{Manifest.permission.CAMERA , Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.rebuildDatabase();

        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        // keep layout when keyboard is shown
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        Intent intent = getIntent();
        // Get the extras (if there are any)
        Bundle extras = intent.getExtras();

        /*if(extras != null) {
            setLoginMessage((boolean) getIntent().getSerializableExtra("loginMessage"));
        }*/
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public void setBottomNavigationVisibility(int viewID){
        navView.setVisibility(viewID);
    }

    public BottomNavigationView getNavView(){
        return navView;
    }

    /*public boolean isLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(boolean loginMessage) {
        this.loginMessage = loginMessage;
    }*/
}
