package com.example.recipesearch.ui.APIComunication;

import android.os.AsyncTask;

import com.example.recipesearch.ui.MealPlanMaker.GeneratedMealPlan;
import com.example.recipesearch.ui.MealPlanMaker.MealPlanActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class MealPlanGeneration extends AsyncTask<Void, Void, String>
{
    private static String Nutrient1 = null;
    private static String Nutrient2 = null;
    private static String Nutrient3 = null;
    private static String Nutrient4 = null;
    private static String id1 = null;
    private static String title1 = null;
    private static String rim1 = null;
    private static String image1 = null;
    private static String id2 = null;
    private static String title2 = null;
    private static String rim2 = null;
    private static String image2 = null;
    private static String id3 = null;
    private static String title3 = null;
    private static String rim3 = null;
    private static String image3 = null;


    @Override
    protected String doInBackground(Void... voids)
    {
        String URL = null;
        String Exclu = null;
        String Diet = null;
        String Cal = null;
        String TF = null;
        String responseData = "";
        Response response = null;
        if (MealPlanActivity.getTimePeriod() != null)
        TF = new String(MealPlanActivity.getTimePeriod().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        if (MealPlanActivity.getCaloricNum() != null)
        Cal = new String(MealPlanActivity.getCaloricNum().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        if (MealPlanActivity.getDietaryPrefrence() != null)
        Diet = new String(MealPlanActivity.getDietaryPrefrence().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        if (MealPlanActivity.getExclusions() != null)
        Exclu = new String(MealPlanActivity.getExclusions().trim().replace(" ", "%20").replace("&", "%26")
                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                .replace("|", "%7C").replace("}", "%7D"));
        if(TF != null && Cal != null && Exclu != null && Diet != null)
        URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?timeFrame="+TF+"&targetCalories="+Cal+"&diet="+Diet+"&exclude="+Exclu;
        else if (TF != null && Cal != null && Diet != null)
            URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?timeFrame="+TF+"&targetCalories="+Cal+"&diet="+Diet;
        else if (TF != null && Cal != null )
            URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?timeFrame="+TF+"&targetCalories="+Cal;
        else if (TF != null )
            URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?timeFrame="+TF;
        else
            URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?timeFrame=day";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
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
        JSONObject jObject1 = null;
        JSONObject jObject2 = null;
        JSONObject jObject3 = null;
        JSONObject jObjectNutrient = null;
        JSONArray jArray = null;
        try
        {
            //this response data is for testing only
            //responseData = "{\"meals\":[{\"id\":1158522,\"title\":\"Rum Raisin Scones\",\"readyInMinutes\":508,\"servings\":8,\"image\":\"rum-raisin-scones-1158522.jpg.jpg.jpg.jpg\",\"imageUrls\":[\"rum-raisin-scones-1158522.jpg.jpg.jpg.jpg\",\"rum-raisin-scones-1158522.jpg.jpg.jpg\",\"rum-raisin-scones-1158522.jpg.jpg\",\"rum-raisin-scones-1158522.jpg\"]},{\"id\":655269,\"title\":\"Peanut Butter Chocolate Cream Pie\",\"readyInMinutes\":45,\"servings\":6,\"image\":\"Peanut-Butter-Chocolate-Cream-Pie-655269.jpg\",\"imageUrls\":[\"Peanut-Butter-Chocolate-Cream-Pie-655269.jpg\"]},{\"id\":716429,\"title\":\"Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs\",\"readyInMinutes\":45,\"servings\":2,\"image\":\"pasta-with-garlic-scallions-cauliflower-bread-crumbs-716429.jpg\",\"imageUrls\":[\"pasta-with-garlic-scallions-cauliflower-bread-crumbs-716429.jpg\"]}],\"nutrients\":{\"calories\":1754.4,\"protein\":44.09,\"fat\":70.38,\"carbohydrates\":233.06}}";
            jObject = new JSONObject(String.valueOf(responseData));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        try
        {
            jArray = jObject.getJSONArray("meals");
            jObject1 = jArray.getJSONObject(0);
            jObject2 = jArray.getJSONObject(1);
            jObject3 = jArray.getJSONObject(2);
            jObjectNutrient = jObject.getJSONObject("nutrients");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        try {
            id1 = jObject1.getString("id");
            title1 = jObject1.getString("title");
            rim1 = jObject1.getString("readyInMinutes");
            image1 = jObject1.getString("image");
            id2 = jObject2.getString("id");
            title2 = jObject2.getString("title");
            rim2 = jObject2.getString("readyInMinutes");
            image2 = jObject2.getString("image");
            id3 = jObject3.getString("id");
            title3 = jObject3.getString("title");
            rim3 = jObject3.getString("readyInMinutes");
            image3 = jObject3.getString("image");
            Nutrient1 = jObjectNutrient.getString("calories");
            Nutrient2 = jObjectNutrient.getString("protein");
            Nutrient3 = jObjectNutrient.getString("fat");
            Nutrient4 = jObjectNutrient.getString("carbohydrates");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        GeneratedMealPlan.setCals(Nutrient1);
        GeneratedMealPlan.setFat(Nutrient3);
        GeneratedMealPlan.setCarbohydrates(Nutrient4);
        GeneratedMealPlan.setProtin(Nutrient2);
        GeneratedMealPlan.setMeal1Name(title1);
        GeneratedMealPlan.setMeal2Name(title2);
        GeneratedMealPlan.setMeal3Name(title3);
        GeneratedMealPlan.setReadyInMin1(rim1);
        GeneratedMealPlan.setReadyInMin2(rim2);
        GeneratedMealPlan.setReadyInMin3(rim3);
        GeneratedMealPlan.setMealImg1(image1);
        GeneratedMealPlan.setMealImg2(image2);
        GeneratedMealPlan.setMealImg3(image3);
        GeneratedMealPlan.setID1(id1);
        GeneratedMealPlan.setID2(id2);
        GeneratedMealPlan.setID3(id3);
        return null;
    }

}
