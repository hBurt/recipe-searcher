package com.example.recipesearch.ui.MealPlanMaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.recipesearch.R;

public class LoadingScreenMP extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen_mp);
    }
    public void destroy()
    {
        finish();
    }
}
