package com.example.recipesearch.ui.search_result;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipesearch.MainActivity;
import com.example.recipesearch.ui.APIComunication.Request_Handler;
import com.example.recipesearch.ui.APIComunication.SearchSettingsActivity;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.Settings.settings_activity;
import com.example.recipesearch.ui.meal_planner.Meal_Planner_Activity;
import com.example.recipesearch.ui.recipe.RecipeActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class SearchActivity extends AppCompatActivity
{
    private SearchView FsearchView;
    private ArrayAdapter silAd;
    private ListView list;
    Toolbar tool;
    private static Handler h;
    Button settings;
    public static String SearchedFood = null; // static to share the query
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);
        tool = findViewById(R.id.tb);
        tool.inflateMenu(R.menu.menu_search);
        settings = findViewById(R.id.Settings_Title);
        list = findViewById(R.id.listView);// list view use and creation of the adapter for iz
        ArrayList<String> foodArray = new ArrayList<>();
        foodArray.addAll(Arrays.asList(getResources().getStringArray(R.array.food_stuff)));
        silAd = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, foodArray);
        list.setAdapter(silAd);


        FsearchView = findViewById(R.id.searchFood);
        FsearchView.setQueryHint("Search Food");
        h = new  Handler(){
            @Override
            public void handleMessage(Message msg)
            {

                Intent in = new Intent(SearchActivity.this, RecipeActivity.class);
                startActivity(in);
            }
        };
        FsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                SearchedFood = query;
                Request_Handler req = new Request_Handler();
                req.execute(); // handles the search query
                RecipeActivity.triggerRefresh(true);
                h.sendEmptyMessageDelayed(0, 1100);// a delay to allow the search to finish before the recipe page pops up
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                silAd.getFilter().filter(newText);
                return false;
            }
        });
        settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(SearchActivity.this, settings_activity.class);
                startActivity(in);
            }
        });
    }
    public static String getSearchedFood()
    {
     return SearchedFood;
    }
    public static void setSearchedFood(String dish){ SearchedFood = dish;}


}
