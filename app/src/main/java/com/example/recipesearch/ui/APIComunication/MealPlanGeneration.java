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
    static String Nutrient1 = null;
    static String Nutrient2 = null;
    static String Nutrient3 = null;
    static String Nutrient4 = null;
    static String id1 = null;
    static String title1 = null;
    static String rim1 = null;
    static String image1 = null;
    static String id2 = null;
    static String title2 = null;
    static String rim2 = null;
    static String image2 = null;
    static String id3 = null;
    static String title3 = null;
    static String rim3 = null;
    static String image3 = null;

    @Override
    protected String doInBackground(Void... voids)
    {
        String responseData = "";
        Response response = null;
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
                Nutrient1 = oneObject.getString("calories");
                Nutrient2 = oneObject.getString("protein");
                Nutrient3 = oneObject.getString("fat");
                Nutrient4 = oneObject.getString("carbohydrates");
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
                id1 = oneObject.getString("id");
                title1 = oneObject.getString("title");
                rim1 = oneObject.getString("readyInMinutes");
                image1 = oneObject.getString("image");
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
                id2 = oneObject.getString("id");
                title2 = oneObject.getString("title");
                rim2 = oneObject.getString("readyInMinutes");
                image2 = oneObject.getString("image");
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
                id3 = oneObject.getString("id");
                title3 = oneObject.getString("title");
                rim3 = oneObject.getString("readyInMinutes");
                image3 = oneObject.getString("image");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static String getCals() {return Nutrient1;}
    public static String getProtin(){return Nutrient2;}
    public static String getFat() {return Nutrient3;}
    public static String getCarbohydrates() {return Nutrient4;}
    public static String getId1() {return id1;}
    public static String getTitle1() {return title1;}
    public static String getRim1() {return rim1;}
    public static String getImage1() {return image1;}
    public static String getId2() {return id2;}
    public static String getTitle2() {return title2;}
    public static String getRim2() {return rim2;}
    public static String getImage2() {return image2;}
    public static String getId3() {return id3;}
    public static String getTitle3() {return title3;}
    public static String getRim3() {return rim3;}
    public static String getImage3() {return image3;}
}
