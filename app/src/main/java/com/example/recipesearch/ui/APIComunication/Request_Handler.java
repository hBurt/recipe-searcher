package com.example.recipesearch.ui.APIComunication;

import android.os.AsyncTask;

import com.example.recipesearch.ui.search_result.SearchActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Request_Handler extends AsyncTask<Void, Void, String>
{
    //API calls work, working on parsing and using the returns, will make 2 calls plus the similar recipe calls
    //the similar recipe calls will be handled in a different class but i want to save the extra returns for latter us
    // might create a random recipe func as the API does support that
    String API_KEY = "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216";
    String API_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?number=5&offset=0&instructionsRequired=true&query=";
    static String id = null;
    static String dishName = null;
    static  String id2 = null;
    static  String id3 = null;
    static  String id4 = null;
    static  String id5 = null;
    static String oneObjectsItem = null;
    static String oneObjectsItem2 = null;
    static String oneObjectsItem3 = null;
    static String oneObjectsItem4 = null;
    static String instructionDetail = "false"; // for use in filtering how detailed the instructions are
    String responseData;
    String responseData2;
    @Override
    protected String doInBackground(Void... voids)
    {
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
            responseData = response.body().string();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // begins parsing to get the id, will just get the first id
        JSONObject jObject = null;
        JSONArray jArray = null;
        try
        {
            jObject = new JSONObject(String.valueOf(responseData));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        try
        {
            jArray = jObject.getJSONArray("results");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
                // Begin getting shit from the arrays
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
                                // this is a test id
                                id = "324694";
                                // Crap
                            }
                        }
        // will create a second api call
        // after received must get the item id from what was received
        if (oneObjectsItem.length() > 0)
        {
            id = oneObjectsItem;
        }
        else
        {
        // this is a test id
         id = "324694";
        }
        if (oneObjectsItem2 .length() > 0)
            dishName = oneObjectsItem2;
        // for having them be on different calls
        // wip for getting the instructions
        OkHttpClient client2 = new OkHttpClient();
        Response response2 = null; // needs an id num or will cause an error
        com.squareup.okhttp.Request request2 = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/analyzedInstructions?stepBreakdown="+ instructionDetail) // will fail if not given an id
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();
        try
        {
            response2 = client2.newCall(request2).execute();
            responseData2 = response2.body().string();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // this section will get the second response
        // after the second one is done i will work on how to use the data where it is needed
        JSONObject jObject2 = null;
        JSONArray jArray2 = null;
        String test = "Should Change"; // for testing if i get any change to the jObject2
        try
        {
            test = responseData2;
            jObject2 = new JSONObject(String.valueOf(responseData2));
            test = jObject2.toString(); // test to check for changes
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        try
        {
            jArray2 = jObject2.getJSONArray("0:");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

       return null;
    }
    public static String getID()
    {
        if (id.length() > 0)
        return id;
        else {
            id = "324694"; // default test id
        return id;
        }
    }
    public static String getDishName()
    {
        return dishName;
    }


}
