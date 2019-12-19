package com.example.recipesearch.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipesearch.ui.Settings.settings_activity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class SearchSettingsActivity extends AppCompatActivity
{
    settings_activity set = new settings_activity();// to use the getSwitchA/B functions for changing how the search is handled
    boolean SearchingDishes = true;
    boolean SearchingIngredients = false;
    //if both are true it will default to Dishes as it comes first
    boolean SearchingByID = false;
    String Key = "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216";
    String URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?number=5&offset=0&instructionsRequired=true&query=";
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());// Get the intent, verify the action and get the query
        Intent intent = getIntent();
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
   // public String[] DoSearch(String query)

   public void DoSearch(final String query)
    {
        String SearchedTerm = query;
        SearchedTerm = new String(query.trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        String url = URL + SearchedTerm + Key ;
        // com.android.volley. this seemes to solve some issues with Volley
        // Will call the Request_Handler class funcs for api communication here
    }
    public String searchSimilar(int ID)
    //Should only be used from a Recipe screen, DO NOT SEARCH BY ID BEFORE OPENING A RECIPE SCREEN, THIS NEEDS THE INT ID
    {
        String result = new String();
        result = APISearchSimilar(ID);
        return result;
    }
    public boolean onSearchRequested() {
        Bundle appData = new Bundle();
        appData.putBoolean(SearchSettingsActivity.INPUT_SERVICE, true);
        startSearch(null, false, appData, false);
        return true;
    }
    private String[] APISearchDish(String query)// will search baised off dish
    {
        //* The code Snippets for the API searches are from the https://rapidapi.com/spoonacular/api/recipe-food-nutrition?endpoint=55e1b24ae4b0a29b2c36073c
        //* only deference is the changes for the input of a val, string or int, for the search, will likely be removed as i get the api to work
        OkHttpClient client = new OkHttpClient();
        String result = new String();
        String[] FoodList = new String[5];
        String Result = null;
        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?number=5&offset=0&type=main%20course&query="+query)
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
        //test func
        result = "test";
        for (int i = 0; i < 5; i++)
        {
            FoodList[i] = result;
        }
        return FoodList;
    }
    private String[]  APISearchIngredient(String query)//will search baissed off ingredient
    {
        OkHttpClient client = new OkHttpClient();
        String result = new String();
        String[] FoodList = new String[4];
        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?number=5&offset=0&instructionsRequired=true&query="+query)
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
        //test func
        result = "test";
        for (int i = 0; i < 5; i++)
        {
            FoodList[i] = result;
        }
        return FoodList;
    }
    private String APISearchSimilar (int ID)//search baised off similar, should make a seperate file, MUST PASS THE ID OF THE CURRENT DISH!!!
    {
        String searchedFood = new String();
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
        //test return
       searchedFood = "Test";
        return searchedFood;
    }

}
