package com.example.recipesearch.ui.recipe;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;

public class RecipeActivity extends AppCompatActivity
{
    Recipe_Directions_Tab_Fragment RDTF = new Recipe_Directions_Tab_Fragment(); // case 1
    Recipe_Ingredient_Tab_Fragment RITF = new Recipe_Ingredient_Tab_Fragment(); // case 0
    Recipe_Similar_Recipes_Tab_Fragment RSRTF = new Recipe_Similar_Recipes_Tab_Fragment(); //case 2
    private TextView tex;
    private ImageView pic;
    private TabLayout tab;
    private TextView TTM;
    Drawable image = null;
    ViewPager view;
    static SharedPreferences mPrefs;
    SharedPreferences.Editor edit;
    static String ID = null;
    static boolean refreshNeeded = false;
    static String RecipeName = "Beef Salpicao"; // example for test purposes
    String defaultName =  "Beef Salpicao";
    static String imgName = " ";
    static String timeToMake = "XX";
    String wantedImg;
    String recipeTitle = null;
    public static int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        if ( refreshNeeded == true)
        {
            refreshNeeded = false;
            refresh();
        }
        mPrefs = getApplicationContext().getSharedPreferences("Recipe_Book", MODE_PRIVATE);
        view = findViewById(R.id.viewPager);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        TTM = findViewById(R.id.Time);
        TTM.setText(timeToMake + " minutes");
        wantedImg = imgName;
        Picasso.get().load(wantedImg).into(pic);
        tab.addTab(tab.newTab().setText("Ingredients"));
        tab.addTab(tab.newTab().setText("Directions"));
        tab.addTab(tab.newTab().setText("Next Recipe"));
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
    public static void setID(String id)
    {
        ID = id;
    }
    public static void useOld(String id)
    {
        if (mPrefs.contains("Note"))
            ID = mPrefs.getString(id+"id", " ");
        if (mPrefs.contains("Day1"))
            Recipe_Directions_Tab_Fragment.setDirections( mPrefs.getString(id+"Directions"," "));
        if (mPrefs.contains("Day2"))
             Recipe_Ingredient_Tab_Fragment.setIngredients( mPrefs.getString(id+"Ingredients"," "));
        if (mPrefs.contains("Note"))
            RecipeName = mPrefs.getString(id+"Name", " ");
        if (mPrefs.contains("Day1"))
            timeToMake = mPrefs.getString(id+"Time"," ");
        if (mPrefs.contains("Day2"))
            imgName = mPrefs.getString(id+"img"," ");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (ID != null)
        {
        edit = mPrefs.edit();
        edit.putString(ID+"id", ID);
        edit.putString(ID+"Directions", Recipe_Directions_Tab_Fragment.getDirections());
        edit.putString(ID+"Ingredients", Recipe_Ingredient_Tab_Fragment.getIngredients());
        edit.putString(ID+"Name", RecipeName);
        edit.putString(ID+"Time", timeToMake);
        edit.putString(ID+"img", imgName);
        edit.apply();
        }
    }
}
