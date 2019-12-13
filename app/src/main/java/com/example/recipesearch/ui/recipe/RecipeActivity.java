package com.example.recipesearch.ui.recipe;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesearch.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class RecipeActivity extends AppCompatActivity
{
    private TextView tex;
    private ImageView pic;
    private TabLayout tab;
    private TabItem tab1;
    private TabItem tab2;
    private TabItem tab3;
    private String RecipeName = "Place_Holder";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        TabItem tab1 = findViewById(R.id.Ingredients);
        TabItem tab2 = findViewById(R.id.Directions);
        TabItem tab3 = findViewById(R.id.Similar_Recipes);
        tex.setText(RecipeName);
    }

}
