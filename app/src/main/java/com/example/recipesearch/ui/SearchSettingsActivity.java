package com.example.recipesearch.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.recipesearch.ui.Settings.settings_activity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class SearchSettingsActivity extends Activity
{
    settings_activity set = new settings_activity();// to use the getSwitchA/B functions for changing how the search is handled
    boolean SearchingDishes = true;
    boolean SearchingIngredients = false;
    //if both are true it will default to Dishes as it comes first
    boolean SearchingByID = false;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra(SearchManager.QUERY);
            DoSearch(query); //will be used to well preform the search
        }
    }

    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search data
            DoSearch(query);
        }
    }
    public void DoSearch(String query)
    {
        String defDish = "Burger";//default Dish search
        String defIngr = "Potato";//Default Ingredient Search
        SearchingDishes = set.GetSwitchA();
        SearchingIngredients = set.GetSwitchB();
        if (SearchingDishes == true)
        {
            if(query != null || query.length() > 0)
                APISearchDish(query);
            else
                APISearchDish(defDish);
        }
        else if (SearchingIngredients == true)
        {
            if(query != null || query.length() > 0)
                APISearchIngredient(query);
            else
                APISearchIngredient(defIngr);
        }
    }
    public void searchSimilar(int ID)
    //Should only be used from a Recipe screen, DO NOT SEARCH BY ID BEFORE OPENING A RECIPE SCREEN, THIS NEEDS THE INT ID
    {
        APISearchSimilar(ID);
    }
    public boolean onSearchRequested() {
        Bundle appData = new Bundle();
        appData.putBoolean(SearchSettingsActivity.INPUT_SERVICE, true);
        startSearch(null, false, appData, false);
        return true;
    }
    private    void APISearchDish(String query)// will search baised off dish
    {
        //* The code Snippets for the API searches are from the https://rapidapi.com/spoonacular/api/recipe-food-nutrition?endpoint=55e1b24ae4b0a29b2c36073c
        //* only deference is the changes for the input of a val, string or int, for the search
        OkHttpClient client = new OkHttpClient();
        String Result = null;
        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?diet=vegetarian&excludeIngredients=coconut&intolerances=egg%252C%20gluten&number=10&offset=0&type=main%20course&query="+query)
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();

        try
        {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void  APISearchIngredient(String query)//will search baissed off ingredient
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ranking=1&ignorePantry=false&ingredients="+query)
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();
        try
        {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void APISearchSimilar (int ID)//search baised off similar, should make a seperate file, MUST PASS THE ID OF THE CURRENT DISH!!!
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"+ID+"/similar")
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();

        try
        {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
