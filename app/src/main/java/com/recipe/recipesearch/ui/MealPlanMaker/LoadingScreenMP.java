package com.recipe.recipesearch.ui.MealPlanMaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.recipe.recipesearch.R;

public class LoadingScreenMP extends AppCompatActivity
{
    public static Activity LSMP;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen_mp);
        LSMP = this;
    }
    public void destroy()
    {
        finish();
    }
}
