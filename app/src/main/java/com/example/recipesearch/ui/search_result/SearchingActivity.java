package com.example.recipesearch.ui.search_result;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.recipesearch.R;

public class SearchingActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
    }
    public void destroy()
    {
        finish();
    }
}
