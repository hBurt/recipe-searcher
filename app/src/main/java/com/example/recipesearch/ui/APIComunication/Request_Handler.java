package com.example.recipesearch.ui.APIComunication;


import android.content.Intent;
import android.os.AsyncTask;


import com.example.recipesearch.ui.recipe.RecipeActivity;
import com.example.recipesearch.ui.recipe.Recipe_Directions_Tab_Fragment;
import com.example.recipesearch.ui.recipe.Recipe_Ingredient_Tab_Fragment;
import com.example.recipesearch.ui.search_result.SearchActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.StringTokenizer;


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
    static String Directions;
    static String Ingredients;
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
            String test = responseData;

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
        String newReturn = " ";
       // id = "521510"; // testing id
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
            newReturn = responseData2;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // this is for testing without making api calls
        //newReturn = "[{\"name\":\"\",\"steps\":[{\"number\":1,\"step\":\"Place the beef cubes in a bowl. Add the minced garlic, pepper and 3 tbsps. of olive oil. Mix well. Cover and keep in the fridge for a couple of hours.Heat the butter and olive oil in a wide shallow pan — wide enough to contain the beef cubes in a single layer. The heat should be very high.Place the beef in a plastic freezer bag. Add the flour. Shake to coat each piece of meat with flour. It is the flour that will thicken the sauce later and make it stick well to the beef.When the olive oil and butter are hot, add the floured beef, spreading the pieces so that every piece touches the oil. Do not stir for a minute or so to allow the underside to brown. Keep the heat very high. Stir and cook for a few minutes, with occasional stirring, until the beef changes color and a light crust forms.Pour in the Worcestershire sauce and liquid seasoning. Stir briskly; the sauce should thicken quite fast. Add the mushrooms, cook just until heated, stirring occasionally.The actual cooking should take no more than five minutes. If you overcook the beef, the meat will turn tough and dry.Transfer the beef salpicao to a serving platter, sprinkle with toasted garlic bits and finely sliced onion leaves. Serve hot with rice.\",\"ingredients\":[{\"id\":6971,\"name\":\"worcestershire sauce\",\"image\":\"dark-sauce.jpg\"},{\"id\":11291,\"name\":\"green onions\",\"image\":\"spring-onions.jpg\"},{\"id\":11260,\"name\":\"mushrooms\",\"image\":\"mushrooms.png\"},{\"id\":4053,\"name\":\"olive oil\",\"image\":\"olive-oil.jpg\"},{\"id\":1042027,\"name\":\"seasoning\",\"image\":\"seasoning.png\"},{\"id\":1001,\"name\":\"butter\",\"image\":\"butter-sliced.jpg\"},{\"id\":11215,\"name\":\"garlic\",\"image\":\"garlic.png\"},{\"id\":1002030,\"name\":\"pepper\",\"image\":\"pepper.jpg\"},{\"id\":20081,\"name\":\"all purpose flour\",\"image\":\"flour.png\"}],\"equipment\":[{\"id\":404783,\"name\":\"bowl\",\"image\":\"bowl.jpg\"},{\"id\":404645,\"name\":\"frying pan\",\"image\":\"pan.png\"}],\"length\":{\"number\":5,\"unit\":\"minutes\"}}]}]";
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
                .replace("image", "").replace(".jpg", "")
                .replace("\"", " ").replace(".png", ""));
        StringTokenizer tokens = new StringTokenizer(secondReturn, ":");
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        String firstDirect = " ";
        String firstIngred = " ";
        String time = " ";
        while ( tokens.hasMoreTokens() )
        {
            result[i++] = tokens.nextToken();
        }
        for (int x = 0; x < result.length; x++)
        {
            if (result[x].length() > 30 )
            {
                firstDirect += result[x];
            }
            else if (result[x].length() < 3 )
            {
                time += result[x];
            }
            else {
                firstIngred += result[x];
            }
        }
        Directions = new String(firstDirect.trim().replace("ingredients", ""));
        Ingredients = new String(firstIngred.trim().replace("null", "").replace("id", "").replace("steps", "").replace("length", "")
                .replace("0", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "")
                .replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "")
                .replace("step", "").replace("minutes", "").replace("equipment", ""));
        RecipeActivity.setPic(oneObjectsItem4);//work on setting an img
        RecipeActivity.setTime(oneObjectsItem3);
        RecipeActivity.triggerRefresh(true);
        RecipeActivity.setRecipeName(oneObjectsItem2);
        Recipe_Directions_Tab_Fragment.setDirections(Directions);
        Recipe_Ingredient_Tab_Fragment.setIngredients(Ingredients);
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
    public static String getDirections()
    {
        return Directions;
    }
    public static String getIngredients()
    {
        return Ingredients;
    }

}
