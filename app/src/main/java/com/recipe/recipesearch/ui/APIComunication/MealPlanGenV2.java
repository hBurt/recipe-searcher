package com.recipe.recipesearch.ui.APIComunication;

import android.os.AsyncTask;

import com.recipe.recipesearch.ui.MealPlanMaker.GeneratedMealPlan;
import com.recipe.recipesearch.ui.MealPlanMaker.MealPlanActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MealPlanGenV2 extends AsyncTask<Void, Void, String>
{
    String apiKey = "829c5610db454ca091cbd571f9cbbf61";
    String apiKey2 = "165a81b84213461a8702d3ec687eacb6";
    String apiKey3 = "46953957ae604aeba07e605696eef0cc";
    String apiKey4 = "db8b4af6c4224c28a2f47fa262d8cf13";
    String apiKey5 = "14676380de0c4536ac53764af2e2cde0";
    String URLFront = "https://api.spoonacular.com/recipes/mealplans/generate?";
    String TOD = "timeFrame=";
    String TargetCal = "targetCalories=";
    String Diet = "diet=";
    String Exclusions = "exclude=";
    String apiKeyURL = "&apiKey=";
    String UserTOD = null;
    String UserTargetCal =null;
    String UserDiet =null;
    String UserExclusions = null;
    String builtURL = null;
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
        String responseData = "";
        Response response = null;
        if (MealPlanActivity.getTimePeriod() != null)
            UserTOD = MealPlanActivity.getTimePeriod().trim().replace(" ", "%20").replace("&", "%26")
                    .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                    .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                    .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                    .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                    .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                    .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                    .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                    .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                    .replace("|", "%7C").replace("}", "%7D");
        if (MealPlanActivity.getCaloricNum() != null)
            UserTargetCal = MealPlanActivity.getCaloricNum().trim().replace(" ", "%20").replace("&", "%26")
                    .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                    .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                    .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                    .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                    .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                    .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                    .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                    .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                    .replace("|", "%7C").replace("}", "%7D");
        if (MealPlanActivity.getDietaryPrefrence() != null)
            UserDiet = MealPlanActivity.getDietaryPrefrence().trim().replace(" ", "%20").replace("&", "%26")
                    .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                    .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                    .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                    .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                    .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                    .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                    .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                    .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                    .replace("|", "%7C").replace("}", "%7D");
        if (MealPlanActivity.getExclusions() != null)
            UserExclusions = MealPlanActivity.getExclusions().trim().replace(" ", "%20").replace("&", "%26")
                    .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
                    .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
                    .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
                    .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
                    .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
                    .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
                    .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
                    .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
                    .replace("|", "%7C").replace("}", "%7D");
        if (UserTOD == null)
            UserTOD = "Day";
        if (UserExclusions == null && UserDiet == null && UserTargetCal == null)
        {
            builtURL = URLFront + TOD+ UserTOD+apiKeyURL + apiKey3;
        }
        else if (UserExclusions == null)
        {
            builtURL = URLFront + TOD+ UserTOD+ "&"+ TargetCal + UserTargetCal + "&"+ Diet + UserDiet + apiKeyURL + apiKey3;
        }
        else if (UserDiet == null)
        {
            builtURL = URLFront + TOD+ UserTOD+ "&"+ TargetCal + UserTargetCal + "&"+ Exclusions + UserExclusions + apiKeyURL + apiKey3;
        }
        else if (UserTargetCal == null)
        {
            builtURL = URLFront + TOD+ UserTOD+ "&"+ Diet + UserDiet + "&" + Exclusions + UserExclusions + apiKeyURL + apiKey3;
        }
        else
            {
                builtURL = URLFront + TOD+ UserTOD+ "&"+ TargetCal + UserTargetCal +"&"+ Diet + UserDiet + "&" + Exclusions + UserExclusions + apiKeyURL + apiKey3;
            }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(builtURL)
                .method("GET", null)
                .build();
        try {
            response = client.newCall(request).execute(); // provides a val for the first search and preforms it
            responseData = response.body().string();
        } catch (IOException e) {
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
            jObject = new JSONObject(responseData);
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
