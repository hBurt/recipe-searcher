package com.example.recipesearch.ui.APIComunication;

import android.os.AsyncTask;

import com.example.recipesearch.ui.recipe.RecipeActivity;
import com.example.recipesearch.ui.recipe.Recipe_Directions_Tab_Fragment;
import com.example.recipesearch.ui.recipe.Recipe_Ingredient_Tab_Fragment;
import com.example.recipesearch.ui.user.MealPlanActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MealPlanGeneration extends AsyncTask<Void, Void, String>
{


    @Override
    protected String doInBackground(Void... voids)
    {
        String responseData = "";
        Response response = null;
        String oneObjectsItem = null;
        String oneObjectsItem2 = null;
        String oneObjectsItem3 = null;
        String oneObjectsItem4 = null;//1-4 are nutrient info
        String oneObjectsItem5 = null;
        String oneObjectsItem6 = null;
        String oneObjectsItem7 = null;
        String oneObjectsItem8 = null;// 5-8 are meal 1
        String oneObjectsItem9 = null;
        String oneObjectsItem10 = null;
        String oneObjectsItem11 = null;
        String oneObjectsItem12 = null;// 9-12 are meal 2
        String oneObjectsItem13 = null;
        String oneObjectsItem14 = null;
        String oneObjectsItem15 = null;
        String oneObjectsItem16 = null;// 13-16 are meal 3
        String TF = new String(MealPlanActivity.getTimePeriod().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        String Cal = new String(MealPlanActivity.getCaloricNum().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        String Diet = new String(MealPlanActivity.getDietaryPrefrence().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        String Exclu = new String(MealPlanActivity.getExclusions().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?timeFrame="+TF+"&targetCalories="+Cal+"&diet="+Diet+"&exclude="+Exclu)
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();
        try
        {
            response = client.newCall(request).execute(); // provides a val for the first search and preforms it
            responseData = response.body().string();
            String test = responseData;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JSONObject jObject = null;
        JSONArray jArray = null;
        JSONArray jArray2 = null;
        JSONArray jArrayM1 = null;
        JSONArray jArrayM2 = null;
        JSONArray jArrayM3 = null;
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
            jArray = jObject.getJSONArray("meals");
            jArray2 = jObject.getJSONArray("nutrients");
            jArrayM1 = jArray.getJSONArray(0);
            jArrayM2 = jArray.getJSONArray(1);
            jArrayM3 = jArray.getJSONArray(3);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        // Begin getting shit from the arrays
        for (int i=0; i < jArray.length(); i++)
        {
            JSONObject oneObject = null;
            try {
                oneObject = jArray2.getJSONObject(i);
                oneObjectsItem = oneObject.getString("calories");
                oneObjectsItem2 = oneObject.getString("protein");
                oneObjectsItem3 = oneObject.getString("fat");
                oneObjectsItem4 = oneObject.getString("carbohydrates");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        for (int i=0; i < jArray.length(); i++)
        {
            JSONObject oneObject = null;
            try {
                oneObject = jArrayM1.getJSONObject(i);
                oneObjectsItem5 = oneObject.getString("id");
                oneObjectsItem6 = oneObject.getString("title");
                oneObjectsItem7 = oneObject.getString("readyInMinutes");
                oneObjectsItem8 = oneObject.getString("image");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        for (int i=0; i < jArray.length(); i++)
        {
            JSONObject oneObject = null;
            try {
                oneObject = jArrayM2.getJSONObject(i);
                oneObjectsItem9 = oneObject.getString("id");
                oneObjectsItem10 = oneObject.getString("title");
                oneObjectsItem11 = oneObject.getString("readyInMinutes");
                oneObjectsItem12 = oneObject.getString("image");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        for (int i=0; i < jArray.length(); i++)
        {
            JSONObject oneObject = null;
            try {
                oneObject = jArrayM3.getJSONObject(i);
                oneObjectsItem13 = oneObject.getString("id");
                oneObjectsItem14 = oneObject.getString("title");
                oneObjectsItem15 = oneObject.getString("readyInMinutes");
                oneObjectsItem16 = oneObject.getString("image");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
