package com.example.recipesearch.ui.search_result;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.recipesearch.R;
import com.example.recipesearch.api.APICore;
import com.example.recipesearch.api.AsyncResponse;
import com.example.recipesearch.api.BackgroundRequest;
import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.database.User;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.example.recipesearch.ui.CustomRecipes.CustomRecipe;
import com.example.recipesearch.ui.Settings.settings_activity;
import com.example.recipesearch.ui.recipe.RecipeActivity;
import com.example.recipesearch.ui.recipe.RecipeActivityV2;
import com.example.recipesearch.ui.recipe.RecipeStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class SearchActivity extends AppCompatActivity
{
    private SearchView FsearchView;
    private ArrayAdapter silAd;
    private ListView list;
    Toolbar tool;
    private static Handler h;
    public static String SearchedFood = null; // static to share the query
    static SharedPreferences mPrefs;
    SharedPreferences.Editor edit;
    static List<String> IDList;

    User user;

    private Recipe recipe;

    //This latch will be used to wait on
    private static CountDownLatch _latch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);

        user = (User) getIntent().getSerializableExtra("databaseUser");

        _latch = new CountDownLatch(1);



        mPrefs = getApplicationContext().getSharedPreferences("Recipe_Book", MODE_PRIVATE);
        tool = findViewById(R.id.tb);
        tool.inflateMenu(R.menu.menu_search);
        list = findViewById(R.id.suggestList);// list view use and creation of the adapter for iz
        ArrayList<String> foodArray = new ArrayList<>();
        foodArray.addAll(Arrays.asList(getResources().getStringArray(R.array.food_stuff)));
        silAd = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, foodArray);
        list.setAdapter(silAd);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                FsearchView.setQuery(list.getItemAtPosition(position).toString(), false);
            }
        });


        tool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.Settings_BTN:
                        Intent in = new Intent(SearchActivity.this, settings_activity.class);
                        startActivity(in);
                       break;
                    case R.id.CustomBTN:
                        Intent CU = new Intent(SearchActivity.this, CustomRecipe.class);
                        startActivity(CU);
                        break;
                       default:
                           return false;

                }
                return true;
            }
        });
        FsearchView = findViewById(R.id.searchFood);
        FsearchView.setQueryHint("Search Food");
        h = new  Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                if(RecipeActivityV2.getIsOpen() != true)
                {
                Intent in = new Intent(SearchActivity.this, RecipeActivityV2.class);
                in.putExtra("databaseUserr", user);
                in.putExtra("recipe", recipe);
                startActivity(in);
                }
            }
        };
        FsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                APICore api = new APICore();





                SearchedFood = query;
                boolean testb = settings_activity.GetSwitchB();
                boolean testa = settings_activity.GetSwitchA();
                if (testa == testb)
                    settings_activity.setSwitchA(true);
                IDList = new ArrayList<String>();
                RecipeStorage storage = new RecipeStorage(getApplicationContext());
                if (query.contains("random"))
                    api.startRequest(query, BackgroundRequest.SearchType.RANDOM, getBaseContext(), h);
                else
                if (storage.isThisInTheBook())
                {
                    api.startRequest(query, BackgroundRequest.SearchType.RECIPE, getBaseContext(), h);
                }
                else if (settings_activity.GetSwitchA() == true)
                {

                    api.startRequest(query, BackgroundRequest.SearchType.RECIPE, getBaseContext(), h);
                }
                else if (settings_activity.GetSwitchA() == false)
                {

                    api.startRequest(query, BackgroundRequest.SearchType.INGREDIENT, getBaseContext(), h);
                }
                else {

                    api.startRequest(query, BackgroundRequest.SearchType.RECIPE, getBaseContext(), h);
                    }
                h.sendEmptyMessageDelayed(0, 10000);

                recipe = api.getRecipe();

                Intent se = new Intent(SearchActivity.this, SearchingActivity.class);
                startActivity(se);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                silAd.getFilter().filter(newText);
                return false;
            }
        });
    }
    public static String getSearchedFood()
    {
     return SearchedFood;
    }
    public static void setSearchedFood(String dish){ SearchedFood = dish;}
    public static  List<String> getIDList()
    {
        return IDList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
