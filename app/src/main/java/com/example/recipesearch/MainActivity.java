package com.example.recipesearch;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.recipesearch.database.ViewModel.LoginLocalViewModel;
import com.example.recipesearch.database.databinding.Listener;
import com.example.recipesearch.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements Listener {

    private CharSequence message;

    private LoginLocalViewModel loginLocalViewModel;
    private ActivityMainBinding binding;

    private Button butotn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butotn_signup = findViewById(R.id.signup_button_signup);
//
     //   binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
//
    //    binding.setClickListener(this);
//
     //   loginLocalViewModel = ViewModelProviders.of(MainActivity.this).get(LoginLocalViewModel.class);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        // keep layout when keyboard is shown
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

//        loginLocalViewModel.getGetAllUsers().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
//
//                butotn_signup.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        System.out.println("Can do a thing here.");
//                    }
//                });
//
//            }
//        });

    }


    public void setMessage(CharSequence s){
        message = s;
    }

    public CharSequence getMessage(){
        return message;
    }


    @Override
    public void onClick(View view) {

    }
}
