package com.recipe.recipesearch.ui.APIComunication;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.StringTokenizer;
//the issue is the returned string it just too big to get just one id, still working on fixing it
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
    String originalReturn = null;
    String editedReturn = null;
    @Override
    protected String doInBackground(Void... voids)
    {
        String responseData = null;
        id = "521510";
        OkHttpClient client = new OkHttpClient();
        Response response = null; // needs an id num or will cause an error
        com.squareup.okhttp.Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/521510/similar")
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();
        try
        {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            originalReturn = responseData;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        editedReturn = new String(originalReturn.trim().replace("&", "")
                .replace("(", "").replace(")", "").replace(",", " ")
                .replace("!", "").replace("=", "").replace("<", "")
                .replace(">", "").replace("#", "").replace("$", "")
                .replace("'", "").replace("*", "").replace("-", " ")
                .replace("/", "").replace("unit", "").replace("number", "")
                .replace(";", "").replace("?", "").replace("@", "")
                .replace("[", "").replace("\\", "").replace("]", "")
                .replace("_", "").replace("`", "").replace("{", "")
                .replace("|", "").replace("}", "").replace("name", "")
                .replace("image", "").replace(".jpg", "").replace("minutes", "")
                .replace("\"", " ").replace(".png", "").replace("id", ""));
        StringTokenizer tokens = new StringTokenizer(editedReturn, ":");
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        String newID = " ";
        String everythingElse = " ";
        while ( tokens.hasMoreTokens() )
        {
            result[i++] = tokens.nextToken();
        }
        for (int x = 0; x < result.length; x++)
        {
            if (result[x].length() > 4 && result[x].length() < 10 )
            {
                newID = result[x];
            }
            else {
                everythingElse += result[x];
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
