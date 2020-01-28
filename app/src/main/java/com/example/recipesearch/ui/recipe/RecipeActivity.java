package com.example.recipesearch.ui.recipe;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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
    private static Handler h;
    Drawable image = null;
    ViewPager view;
    static SharedPreferences mPrefs;
    SharedPreferences.Editor edit;
    static String ID = null;
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
        mPrefs = getApplicationContext().getSharedPreferences("Recipe_Book", MODE_PRIVATE);
        view = findViewById(R.id.viewPager);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        TTM = findViewById(R.id.Time);
        TTM.setText(timeToMake + " minutes");
        wantedImg = imgName;
        if (wantedImg.length() > 3)
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
        if (mPrefs.contains(id+"Directions"))
            Recipe_Directions_Tab_Fragment.setDirections( mPrefs.getString(id+"Directions"," "));
        if (mPrefs.contains(id+"Ingredients"))
             Recipe_Ingredient_Tab_Fragment.setIngredients( mPrefs.getString(id+"Ingredients"," "));
        if (mPrefs.contains(id+"Name"))
            RecipeName = mPrefs.getString(id+"Name", " ");
        if (mPrefs.contains(id+"Time"))
            timeToMake = mPrefs.getString(id+"Time"," ");
        if (mPrefs.contains(id+"img"))
            imgName = mPrefs.getString(id+"img"," ");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // should save as long as i have the id
        if (ID != null)
        {
        edit = mPrefs.edit();
        if (mPrefs.contains(0+"id"))
        edit.putString(0+"id", ID);
        else {
            for (int i = 0; i > 10; i++)
            {
                if (mPrefs.contains(i+"id"))
                {
                    edit.putString(i + "id", ID);
                    break;
                }
            }
        }
        edit.putString(SearchActivity.getSearchedFood()+"Directions", Recipe_Directions_Tab_Fragment.getDirections());
        edit.putString(SearchActivity.getSearchedFood()+"Ingredients", Recipe_Ingredient_Tab_Fragment.getIngredients());
        edit.putString(SearchActivity.getSearchedFood()+"Name", RecipeName);
        edit.putString(SearchActivity.getSearchedFood()+"Time", timeToMake);
        edit.putString(SearchActivity.getSearchedFood()+"img", imgName);
        edit.apply();
        }
    }
}
