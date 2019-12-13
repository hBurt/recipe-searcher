package com.example.recipesearch.ui.recipe;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesearch.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class RecipeActivity extends AppCompatActivity
{
    Recipe_Directions_Tab_Fragment RDTF = new Recipe_Directions_Tab_Fragment();
    Recipe_Ingredient_Tab_Fragment RITF = new Recipe_Ingredient_Tab_Fragment();
    Recipe_Similar_Recipes_Tab_Fragment RSRTF = new Recipe_Similar_Recipes_Tab_Fragment();
    private TextView tex;
    private ImageView pic;
    private TabLayout tab;
    private TabItem tab1;
    private TabItem tab2;
    private TabItem tab3;
    private String RecipeName = "Test Text";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        //Toolbar tool = findViewById(R.id.Recpie_Tool_Bar);
        //tool.inflateMenu(R.menu.to_home_menu);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        TabItem tab1 = findViewById(R.id.Ingredients);
        TabItem tab2 = findViewById(R.id.Directions);
        TabItem tab3 = findViewById(R.id.Similar_Recipes);
        tex.setText(RecipeName);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                switch (tab.getPosition())
                {
                    case 0:// ingredient

                    case 1: // directions

                    case 2: // similar
                        
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

}
