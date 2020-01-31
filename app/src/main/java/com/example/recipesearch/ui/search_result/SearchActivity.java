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
import androidx.room.Room;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.example.recipesearch.database.LocalLoginDatabase;
import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.database.User;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.example.recipesearch.ui.APIComunication.Ingredient_Request;
import com.example.recipesearch.ui.APIComunication.Request_Handler;
import com.example.recipesearch.ui.recipe.RecipeStorage;
import com.example.recipesearch.ui.Settings.settings_activity;
import com.example.recipesearch.ui.recipe.RecipeActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);

        user = (User) getIntent().getSerializableExtra("databaseUser");

        //user.getFavorites().add(new Favorite(new Recipe(0, "Random title2", 25, "https://thumbs.dreamstime.com/b/indian-bengali-thali-meal-x-consisting-different-curry-flat-bread-rice-papad-77486943.jpg")));

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.rebuildDatabase();

        databaseHelper.getDatabase().getUserDao().updateDetails(user);

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

                Intent in = new Intent(SearchActivity.this, RecipeActivity.class);
                in.putExtra("databaseUserr", user);
                startActivity(in);
                SearchingActivity sercAct = new SearchingActivity();
                sercAct.destroy();
            }
        };
        FsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Intent se = new Intent(SearchActivity.this, SearchingActivity.class);

                startActivity(se);
                SearchedFood = query;
                boolean testb = settings_activity.GetSwitchB();
                boolean testa = settings_activity.GetSwitchA();
                if (testa == testb)
                    settings_activity.setSwitchA(true);
                IDList = new ArrayList<String>();
                RecipeStorage storage = new RecipeStorage(getApplicationContext());
                if (storage.isThisInTheBook())
                {
                    RecipeActivity.setReadTheBook(true);
                    h.sendEmptyMessageDelayed(0, 500);// a delay to allow the search to finish before the recipe page pops up
                }
                else if (settings_activity.GetSwitchA() == true)
                {
                Request_Handler req = new Request_Handler();
                req.execute();// handles the search query
                h.sendEmptyMessageDelayed(0, 3000);// a delay to allow the search to finish before the recipe page pops up
                }
                else if (settings_activity.GetSwitchA() == false)
                {
                    Ingredient_Request IR = new Ingredient_Request();
                    IR.execute();
                    h.sendEmptyMessageDelayed(0, 3000);// a delay to allow the search to finish before the recipe page pops up
                }
                else {
                    Request_Handler req = new Request_Handler();
                    req.execute(); //default search
                    h.sendEmptyMessageDelayed(0, 3000);// a delay to allow the search to finish before the recipe page pops up
                    }
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
