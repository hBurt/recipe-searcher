package com.recipe.recipesearch.ui.APIComunication;

import android.os.AsyncTask;

import com.recipe.recipesearch.ui.recipe.RecipeActivity;
import com.recipe.recipesearch.ui.recipe.Recipe_Directions_Tab_Fragment;
import com.recipe.recipesearch.ui.recipe.Recipe_Ingredient_Tab_Fragment;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class SelectedMealData  extends AsyncTask<Void, Void, String>
{
    private static String id = null;
    private static String dishName = null;
    private static String oneObjectsItem = null;
    private static String oneObjectsItem2 = null;
    private static String oneObjectsItem3 = null;
    private static String oneObjectsItem4 = null;
    private String responseData;
    private static String Directions;
    private static String Ingredients;
    List<String> IDList;
    @Override
    protected String doInBackground(Void... voids)
    {
        String newReturn = " ";
        Response response2 = null;
        OkHttpClient client2 = new OkHttpClient();
        com.squareup.okhttp.Request request2 = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/analyzedInstructions?stepBreakdown=false") // will fail if not given an id
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();
        try {
            response2 = client2.newCall(request2).execute();
            String responseData2 = response2.body().string();
            newReturn = responseData2;
        } catch (IOException e) {
            RecipeActivity.setTime("ERROR:03");
            RecipeActivity.setPic(" ");
            RecipeActivity.setRecipeName("ERROR: Failed to retrieve recipe information");
            Recipe_Directions_Tab_Fragment.setDirections("ERROR: Failed to retrieve recipe information");
            Recipe_Ingredient_Tab_Fragment.setIngredients("ERROR: Failed to retrieve recipe information");
            return null;
        }
        // this is for testing without making api calls
        // this section will get the second response
        // after the second one is done i will work on how to use the data where it is needed
        String secondReturn = " ";
        // this should remove unwanted characters
        secondReturn = new String(newReturn.trim().replace("&", "")
                .replace(",", " ")
                .replace("!", "").replace("=", "").replace("<", "")
                .replace(">", "").replace("#", "").replace("$", "")
                .replace("'", "").replace("*", "").replace("-", " ")
                .replace("/", "").replace("unit", "").replace("number", "")
                .replace(";", "").replace("?", "").replace("@", "")
                .replace("[", "").replace("\\", "").replace("]", "")
                .replace("_", "").replace("`", "").replace("{", "")
                .replace("|", "").replace("}", "").replace("name", "")
                .replace("image", "").replace(".jpg", "").replace("  ", "")
                .replace("\"", " ").replace(".png", "").replace(".", ""));
        StringTokenizer tokens = new StringTokenizer(secondReturn, ":");
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        String firstDirect = " ";
        List<String> firstIngred = new ArrayList<String>();
        String time = " ";
        while (tokens.hasMoreTokens()) {
            result[i++] = tokens.nextToken();
        }
        for (int x = 0; x < result.length; x++) {
            if (result[x].length() > 30) {
                firstDirect += result[x];
            } else if (result[x].length() < 3) {
                time += result[x];
            } else {
                firstIngred.add(result[x]);
            }
        }
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(firstIngred);
        firstIngred.clear();
        firstIngred.addAll(hashSet);
        String secondIngred = firstIngred.toString();
        Directions = new String(firstDirect.trim().replace("ingredients", ""));
        String thirdIngred = new String(secondIngred.trim().replace("null", "").replace("id ", "").replace("steps", "").replace("length", "")
                .replace("0", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "")
                .replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "")
                .replace("step", "").replace("minutes", "").replace("equipment", "").replace(",", "").replace("[", "")
                .replace("]", "").replace("temperature", "").replace("Fahrenheit", "").replace("stove", "").replace("oven", "")
                .replace("Celsius", "").replace("  ", " ").replace(" and ", " ").replace("instant", "").replace(" pot ", "")
                .replace(" kitchen", "").replace(" timer ", "").replace(" Form ", "").replace(" Cook ", "").replace(" Grill ", "")
                .replace(" or ", "").replace(" frying ", "").replace(" pan ", "").replace(" grill ", "").replace(" fry ", "")
                .replace(" fresh ", "").replace(" brown ", "").replace(" the ", ""));
        StringTokenizer tokensb = new StringTokenizer(thirdIngred, " ");
        String[] resultb = new String[tokensb.countTokens()];
        List<String> fourthIngred = new ArrayList<String>();
        int c = 0;
        while (tokensb.hasMoreTokens()) {
            resultb[c++] = tokensb.nextToken();
        }
        for (int a = 0; a < resultb.length; a++) {
            fourthIngred.add(resultb[a]);
        }
        HashSet<String> hashSetb = new HashSet<String>();
        hashSetb.addAll(fourthIngred);
        fourthIngred.clear();
        fourthIngred.addAll(hashSetb);
        String fifthIngred = fourthIngred.toString();
        String sixthIngred = new String(fifthIngred.trim().replace("[", "").replace("]", "").replace(",", " ").replace(".", " ")
                .replace(" in ", "").replace(" dutch ", ""));
        Ingredients = sixthIngred;
        Recipe_Directions_Tab_Fragment.setDirections(Directions);
        Recipe_Ingredient_Tab_Fragment.setIngredients(Ingredients);
        return null;
    }
    public void setId(String s){ id = s;}
}
