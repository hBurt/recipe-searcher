package com.example.recipesearch.ui.APIComunication;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.LruCache;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipesearch.ui.search_result.SearchActivity;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static androidx.core.content.ContextCompat.getSystemService;

public class Request_Handler extends AsyncTask<Void, Void, String>
{
    //API calls work, working on parsing and using the returns, will make 2 calls plus the similar recipe calls
    //the similar recipe calls will be handled in a different class but i want to save the extra returns for latter us
    // might create a random recipe func as the API does support that
    String API_KEY = "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216";
    String API_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?number=5&offset=0&instructionsRequired=true&query=";
    static  String id2 = null;
    static  String id3 = null;
    static  String id4 = null;
    static  String id5 = null;
    @Override
    protected String doInBackground(Void... voids)
    {
        String id = null;
        String RetreivedFood = SearchActivity.getSearchedFood();
        String Food = new String(RetreivedFood.trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        // Do some validation here
        OkHttpClient client = new OkHttpClient();
        final Moshi moshi1 = new Moshi.Builder().build();
        com.squareup.okhttp.Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?number=1&offset=0&instructionsRequired=true&query="+ Food) // will use only the first result
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();

        Response response = null; // needs an id num or will cause an error
        try
        {
            response = client.newCall(request).execute(); // provides a val for the first search and preforms it

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // begins parsing to get the id, will just get the first id
        //WIP RN as the return is multiple arrays nested in another
        //Place code to parse here
       /* JSONObject jObject = null;
        try
        {
            jObject = new JSONObject(String.valueOf(response));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        JSONArray jArray = null;
        try
        {
            jArray = jObject.getJSONArray("Retrieved_Array");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        for (int i=0; i < jArray.length(); i++)
        {
            try
            {
                JSONObject oneObject = jArray.getJSONObject(i);
                // Begin getting shit from the arrays

            }
            catch (JSONException e)
            {
                // Crap
            }
        }*/

        // will create a second api call
        // after received must get the item id from what was received
        // this is a test id
         id = "156992";
        // for having them be on different calls
        OkHttpClient client2 = new OkHttpClient();
        final Moshi moshi2 = new Moshi.Builder().build();
        Response response2 = null; // needs an id num or will cause an error
         request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/analyzedInstructions?stepBreakdown=false") // will fail if not given an id
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();
        try
        {
            response2 = client2.newCall(request).execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
       return null;
    }


}
