package com.recipe.recipesearch.ui.search_result;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.recipe.recipesearch.R;
import com.recipe.recipesearch.api.APICore;
import com.recipe.recipesearch.api.BackgroundRequest;
import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.ui.CustomRecipes.CustomRecipe;
import com.recipe.recipesearch.ui.CustomRecipes.CustomStorage;
import com.recipe.recipesearch.ui.Settings.settings_activity;
import com.recipe.recipesearch.ui.recipe.RecipeActivity;
import com.recipe.recipesearch.ui.recipe.RecipeActivityV2;
import com.recipe.recipesearch.ui.recipe.RecipeStorage;
import com.recipe.recipesearch.ui.recipe.Recipe_Directions_Tab_Fragment;
import com.recipe.recipesearch.ui.recipe.Recipe_Ingredient_Tab_Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class SearchActivity extends AppCompatActivity
{
    private SearchView FsearchView;
    private ArrayAdapter silAd;
    private ListView list;
    private static String previousID = null;
    Toolbar tool;
    private static Handler h;
    public static String SearchedFood = null; // static to share the query
    static SharedPreferences mPrefs;
    SharedPreferences.Editor edit;
    static List<String> IDList;
    private static Recipe simRecipe;
    User user;
    private Recipe recipe;

    //This latch will be used to wait on
    private static CountDownLatch _latch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);
        mPrefs = getSharedPreferences("LastSearch", MODE_PRIVATE);
        user = (User) getIntent().getSerializableExtra("databaseUser");
        simRecipe = null;
        if (mPrefs.contains("LastSearch"))
        {
            previousID = mPrefs.getString("LastSearch", " ");
        }
        _latch = new CountDownLatch(1);
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
                if (query.contains("random") || query.contains("Random"))
                {
                    api.startRequest(query, BackgroundRequest.SearchType.RANDOM, getBaseContext(), h);
                    Intent se = new Intent(SearchActivity.this, SearchingActivity.class);
                    startActivity(se);
                }
                else if (query.contains("Custom") || query.contains("custom"))
                {
                    startCustom();
                }
                else if (query.contains("Similar to Previous Search") ||query.contains("similar to Previous Search")
                        ||query.contains("similar to previous Search") || query.contains("similar to previous search")
                        || query.contains("Similar")|| query.contains("similar"))
                {
                    Recipe recipe = api.getRecipe();
                    api.startRequest(String.valueOf(recipe.getId()), BackgroundRequest.SearchType.NEXT, getApplicationContext(), h);
                    Intent se = new Intent(SearchActivity.this, SearchingActivity.class);
                    startActivity(se);
                }
                else if (storage.isThisInTheBook())
                {
                    api.startRequest(query, BackgroundRequest.SearchType.RECIPE, getBaseContext(), h);
                    h.sendEmptyMessageDelayed(0, 10000);
                    Intent se = new Intent(SearchActivity.this, SearchingActivity.class);
                    startActivity(se);
                }
                else if (settings_activity.GetSwitchA() == true)
                {
                    api.startRequest(query, BackgroundRequest.SearchType.RECIPE, getBaseContext(), h);
                    h.sendEmptyMessageDelayed(0, 10000);
                    Intent se = new Intent(SearchActivity.this, SearchingActivity.class);
                    startActivity(se);

                }
                else if (settings_activity.GetSwitchA() == false)
                {
                    api.startRequest(query, BackgroundRequest.SearchType.INGREDIENT, getBaseContext(), h);
                    h.sendEmptyMessageDelayed(0, 10000);
                    Intent se = new Intent(SearchActivity.this, SearchingActivity.class);
                    startActivity(se);
                }
                else {

                    api.startRequest(query, BackgroundRequest.SearchType.RECIPE, getBaseContext(), h);
                    h.sendEmptyMessageDelayed(0, 10000);
                    Intent se = new Intent(SearchActivity.this, SearchingActivity.class);
                    startActivity(se);
                }

                recipe = api.getRecipe();

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
    private void startCustom()
    {
        CustomStorage Cs = new CustomStorage(getApplicationContext());
        if (Cs.getCount() > 0)
        {
            if (RecipeActivity.getIsOpen() == false)
            {
                Intent in = new Intent(getApplicationContext(), RecipeActivity.class);
                startActivity(in);
            }
            RecipeActivity.setRecipeName("Next Test"); // temp test
            Recipe_Directions_Tab_Fragment.setDirections("Next Text");
            Recipe_Ingredient_Tab_Fragment.setIngredients("Next Text");
            if (Cs.getCName() != null)
            {
                RecipeActivity.setRecipeName(Cs.getCName());
                RecipeActivity.setTime(Cs.getCTime());
                if (Cs.getBool() == true)
                    RecipeActivity.setTakenPio(Cs.getCImgURL());
                else
                    RecipeActivity.setPicUri(Cs.getCImgURL());
                Recipe_Directions_Tab_Fragment.setDirections(Cs.getCDirections());
                String ingred = Cs.getCIngred().trim() .replace(",", " \n ");
                Recipe_Ingredient_Tab_Fragment.setIngredients(ingred);
                Cs.setNum();
                if (RecipeActivity.getIsOpen() == true)
                {
                    h.sendEmptyMessageDelayed(0, 300);
                }
                Toast.makeText(getApplicationContext(), "Recipe: " +Cs.getNum()+" of: "+Cs.getCount(),Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No Custom Recipes stored on this device, Go make Some.",Toast.LENGTH_SHORT).show();
        }

    }
    public void refresh()
    {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
    public static String getPreviousID(){return previousID;}
    public static void setPreviousID(String id){ previousID = id;}

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        edit = mPrefs.edit();
        edit.putString("LastSearch", previousID);
        edit.apply();
    }
    public static void setSimRecipe(Recipe recipe){simRecipe = recipe;}
    public static Recipe getSimRecipe(){return simRecipe;}
}
