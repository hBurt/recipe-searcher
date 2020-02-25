package com.recipe.recipesearch.ui.search_result;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.recipe.recipesearch.R;

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
    public static boolean getIsOpen(){return isOpen;}
    public void refresh()
    {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
