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
import androidx.viewpager.widget.ViewPager;

import com.example.recipesearch.R;
import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.ui.APIComunication.Request_Handler;
import com.example.recipesearch.ui.search_result.SearchActivity;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class RecipeActivity extends AppCompatActivity
{
    Recipe_Directions_Tab_Fragment RDTF = new Recipe_Directions_Tab_Fragment(); // case 1
    Recipe_Ingredient_Tab_Fragment RITF = new Recipe_Ingredient_Tab_Fragment(); // case 0
    Recipe_Similar_Recipes_Tab_Fragment RSRTF = new Recipe_Similar_Recipes_Tab_Fragment(); //case 2
    private TextView tex;
    private ImageView pic;
    private TabLayout tab;
    private TextView TTM;
    ViewPager view;
    static boolean refreshNeeded = false;
    static String RecipeName = "Beef Salpicao"; // example for test purposes
    String defaultName =  "Beef Salpicao";
    static String imgName = null;
    static String timeToMake = "XX";
    String recipeTitle = null;
    public static int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        view = findViewById(R.id.viewPager);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        TTM = findViewById(R.id.Time);
        TTM.setText(timeToMake + " minutes");
        if (imgName != null)
            pic.setImageResource(getResources().getIdentifier(imgName,"drawable", getPackageName()));
        if (refreshNeeded != false)
            refresh();
        tab.addTab(tab.newTab().setText("Ingredients"));
        tab.addTab(tab.newTab().setText("Directions"));
        tab.addTab(tab.newTab().setText("Similar Recipes"));
        tex.setText(RecipeName);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        final MyTabAdapter adapter = new MyTabAdapter(this,getSupportFragmentManager(), tab.getTabCount());
        view.setAdapter(adapter);
        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                view.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                refresh();
            }
        });
    }
    public static void setRecipeName(String someName)
    {
        RecipeName = someName;
    }
    public static String getRecipeName()
    {
        return RecipeName;
    }
    public void refresh()
    {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
    public static void setPic( String img)
    {
        imgName = img;
    }
    public static void triggerRefresh(boolean trigger)
    {
        refreshNeeded = trigger;
    }
    public static void setTime(String time)
    {
        timeToMake = time;
    }
}
