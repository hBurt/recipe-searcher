package com.example.recipesearch.ui.recipe;

import android.os.AsyncTask;

import com.example.recipesearch.ui.APIComunication.Request_Handler;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Next_Similar_Activity extends AsyncTask<Void, Void, String>
{
    // this is basically a third call made by the similar recipes tab to be used when the button is pressed
    // will make a call to get a short list of similar recipes
    private static String id = null;
    private static String name = "";
    private static String oneObjectsItem = null;
    private static String oneObjectsItem2 = null;
    private static String oneObjectsItem3 = null;
    private static String oneObjectsItem4 = null;
    @Override
    protected String doInBackground(Void... voids)
    {
        String responseData = null;
        //id = Request_Handler.getID();
        /*if (id != Recipe_Similar_Recipes_Tab_Fragment.getID())
        {
            id = Recipe_Similar_Recipes_Tab_Fragment.getID();
        }*/
        id = "324694";
        OkHttpClient client = new OkHttpClient();
        Response response = null; // needs an id num or will cause an error
        com.squareup.okhttp.Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"+ id +"/similar")
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();
        try
        {
            response = client.newCall(request).execute();
            responseData = response.body().string();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JSONObject jObject = null;
        JSONArray jArray = null;
        String test = "Should Change"; // for testing if i get any change to the jObject
        try
        {
            jObject = new JSONObject(String.valueOf(responseData));
            test = jObject.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        try
        {
            jArray = jObject.getJSONArray("0:");
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
                oneObjectsItem = oneObject.getString("id");
                oneObjectsItem2 = oneObject.getString("title");
                oneObjectsItem3 = oneObject.getString("readyInMinutes");
                oneObjectsItem4 = oneObject.getString("image");
            }
            catch (JSONException e)
            {
                // Crap
                e.printStackTrace();
            }
        }
        return null;
    }
    public static String getNewID()
    {
        return id;
    }
    public static void setId( String newID)
    {
        id = newID;
    }
    public static String getName()
    {
        return name;
    }
}
