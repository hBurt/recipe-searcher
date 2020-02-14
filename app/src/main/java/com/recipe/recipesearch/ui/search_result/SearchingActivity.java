package com.recipe.recipesearch.ui.search_result;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.recipesearch.R;

public class SearchingActivity extends AppCompatActivity
{
    public static Activity SA;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        SA = this;
    }
    public void destroy()
    {
        finish();
    }
}
