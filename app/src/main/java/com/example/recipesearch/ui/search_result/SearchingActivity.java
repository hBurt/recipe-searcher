package com.example.recipesearch.ui.search_result;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesearch.R;

public class SearchingActivity extends AppCompatActivity
{
    public static Activity SA;
    private static boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        isOpen = true;
        SA = this;
    }
    public void destroy()
    {
        finish();
    }
    public static boolean getIsOpen(){return isOpen;}
}
