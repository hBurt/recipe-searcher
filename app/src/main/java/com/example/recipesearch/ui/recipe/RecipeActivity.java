package com.example.recipesearch.ui.recipe;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.recipesearch.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class RecipeActivity extends AppCompatActivity
{
    Recipe_Directions_Tab_Fragment RDTF = new Recipe_Directions_Tab_Fragment(); // case 1
    Recipe_Ingredient_Tab_Fragment RITF = new Recipe_Ingredient_Tab_Fragment(); // case 0
    Recipe_Similar_Recipes_Tab_Fragment RSRTF = new Recipe_Similar_Recipes_Tab_Fragment(); //case 2
    private TextView tex; // all this shit is declared here because it just made it easy incase it was needed outside onCreate
    private ImageView pic;
    private TabLayout tab;
    private TabItem tab1;
    private TabItem tab2;
    private TabItem tab3;
    private String RecipeName = "Test Text";
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
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
                switch (tab.getPosition())// swaps between the fragments each time a tab is selected
                {
                    case 0:// ingredient
                        transaction.replace(R.id.Tabs, RDTF);
                        transaction.commit();
                        break;
                    case 1: // directions
                        transaction.replace(R.id.Tabs, RSRTF);
                        transaction.commit();
                        break;
                    case 2: // similar
                        transaction.replace(R.id.Tabs, RITF);
                        transaction.commit();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {//not sure what to do here rn, if anything
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {//will do nothing, don't think anything should happen if tabs are reselected
            }
        });
    }

}
