package com.recipe.recipesearch.api;

import android.content.Context;
import android.util.Log;
import android.os.Handler;

import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.database.contents.Amount;
import com.recipe.recipesearch.database.contents.Equipment;
import com.recipe.recipesearch.database.contents.Ingredient;
import com.recipe.recipesearch.database.contents.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class APICore implements AsyncResponse {

    private static final String TAG = "APICore";

    private BackgroundRequest bgRequest;
    private Recipe recipe;
    private BackgroundRequest.SearchType searchType;

    BackgroundRequest.RequestType requestType;

    private Handler handler;

    public APICore(){
        recipe = new Recipe();
    }

    public void startRequest(String request, BackgroundRequest.SearchType searchType, Context context, Handler handler){
        this.searchType = searchType;
        this.handler = handler;
        requestType = BackgroundRequest.RequestType.REQUEST_BASE_RECIPE;
        bgRequest = new BackgroundRequest(request, searchType, context);
        bgRequest.delegate = this;
        bgRequest.execute();
    }

    public void startRequest(int id, BackgroundRequest.RequestType requestType){
        this.requestType = requestType;
        bgRequest = new BackgroundRequest(id, requestType);
        bgRequest.delegate = this;
        bgRequest.execute();
    }

    @Override
    public void processFinish(String output) {
        try {
            switch (requestType){
                case REQUEST_BASE_RECIPE:
                    Log.v(TAG, "[processFinish][base_recipe] : ");
                    buildRecipe(output, searchType);
                    break;
                case REQUEST_BASE_RECIPE_ID:
                    buildRecipe(output, searchType);
                    break;
                case REQUEST_INGREDIENTS:
                    Log.v(TAG, "[processFinish][request_ingredients] : ");
                    buildIngredients(output);
                    break;
                case REQUEST_INSTRUCTIONS:
                    Log.v(TAG, "[processFinish][request_instructions] : ");
                    buildInstructions(output);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void buildRecipe(String apiResponse, BackgroundRequest.SearchType searchType) throws JSONException {

        if(searchType == BackgroundRequest.SearchType.RECIPE) {

            Log.v(TAG, "Start base recipe build");

            JSONObject jsonObjectBase = new JSONObject(apiResponse);
            JSONArray jsonArray = jsonObjectBase.getJSONArray("results");
            JSONObject jsonObject;

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                getRecipe().setId(jsonObject.optInt("id"));
                getRecipe().setTitle(jsonObject.optString("title"));
                getRecipe().setReadyInMiniutes(jsonObject.optInt("readyInMinutes"));
                getRecipe().setImageURL(jsonObject.optString("image"));
            }

            getRecipe().setBaseImageURI(jsonObjectBase.getString("baseUri"));

            Log.v(TAG, "End base build; ID search recipe ingredients: " + getRecipe().getId());
            startRequest(getRecipe().getId(), BackgroundRequest.RequestType.REQUEST_INGREDIENTS);
        } else if(searchType == BackgroundRequest.SearchType.INGREDIENT){

            System.out.println(apiResponse);
            //TODO

        }
    }

    private void buildIngredients(String apiResponse) throws JSONException {
        //https://api.spoonacular.com/recipes/227961/ingredientWidget.json?apiKey=829c5610db454ca091cbd571f9cbbf61

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        //Log.v(TAG, "Begin ingredient build ::" + apiResponse);

        JSONObject jsonObjectBase = new JSONObject(apiResponse);
        JSONArray jsonArray = jsonObjectBase.getJSONArray("ingredients");
        JSONObject jsonObject;

        Ingredient ingredient;
        for (int i = 0; i < jsonArray.length(); i++) {

            ingredient = new Ingredient();

            jsonObject = jsonArray.getJSONObject(i);
            ingredient.setName(jsonObject.optString("name"));
            ingredient.setImage(jsonObject.optString("image"));

            String amountArray = jsonObject.optString("amount");
            JSONObject jsonArrayObjectBase = new JSONObject(amountArray);
            for (int j = 0; j < jsonArrayObjectBase.names().length(); j++) {

                String baseName = jsonArrayObjectBase.names().get(j).toString();
                String jsonAmountBaseAsString = jsonArrayObjectBase.optString(baseName);

                JSONObject amountObject = new JSONObject(jsonAmountBaseAsString);

                double amountValue = amountObject.getDouble("value");

                String amountUnitBase = amountObject.optString("unit");
                String amountUnitFinal = amountUnitBase.equals("") ? ingredient.getName() : amountUnitBase;

                ingredient.getAmount().add(new Amount(amountValue, amountUnitFinal, baseName));
            }

            ingredients.add(ingredient);
        }

        getRecipe().setIngredients(ingredients);

        startRequest(getRecipe().getId(), BackgroundRequest.RequestType.REQUEST_INSTRUCTIONS);
    }

    private void buildInstructions(String apiResponse) throws JSONException {
        //https://api.spoonacular.com/recipes/249358/analyzedInstructions?stepBreakdown=true&apiKey=829c5610db454ca091cbd571f9cbbf61

        String fixedResponse = apiResponse.substring(1, apiResponse.length() -1);

        //Recipe recipe = new Recipe();
        ArrayList<Step> steps = new ArrayList<>();

        JSONObject jsonObjectBase = new JSONObject(fixedResponse);
        JSONArray jsonArray = jsonObjectBase.getJSONArray("steps");
        JSONObject jsonObject;

        Step step;
        for (int i = 0; i < jsonArray.length(); i++) {

            step = new Step();

            jsonObject = jsonArray.getJSONObject(i);

            step.setNumber(jsonObject.optInt("number"));
            step.setStepInstruction(jsonObject.optString("step"));

            JSONArray ingredientstArray = jsonObject.getJSONArray("ingredients");
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            //System.out.println(ingredientstArray.toString());

            for (int j = 0; j < ingredientstArray.length(); j++) {
                Ingredient ingredient = new Ingredient();

                if ((ingredientstArray.getJSONObject(j).length() > 0) && (ingredientstArray.getJSONObject(j) != null)) {
                    jsonObject = ingredientstArray.getJSONObject(j);

                    ingredient.setId(jsonObject.optInt("id"));
                    ingredient.setName(jsonObject.optString("name"));
                    ingredient.setImage(jsonObject.optString("image"));

                    ingredients.add(ingredient);
                }
            }
            step.setIngredients(ingredients);

            jsonObject = jsonArray.getJSONObject(i);

            //System.out.println(jsonObject.toString());

            if (jsonObject.getJSONArray("equipment") != null && jsonObject.getJSONArray("equipment").length() > 0) {

                JSONArray equipArray = jsonObject.getJSONArray("equipment");
                ArrayList<Equipment> equip = new ArrayList<>();

                for (int j = 0; j < equipArray.length(); j++) {
                    Equipment equipment = new Equipment();

                    if ((equipArray.getJSONObject(j).length() > 0) && (equipArray.getJSONObject(j) != null)) {
                        jsonObject = equipArray.getJSONObject(j);

                        equipment.setId(jsonObject.optInt("id"));
                        equipment.setName(jsonObject.optString("name"));
                        equipment.setImage(jsonObject.optString("image"));

                        equip.add(equipment);
                    }
                }
                step.setEquipment(equip);

            }
            steps.add(step);
        }

        getRecipe().setSteps(steps);

        collapseHandler();

        Log.v(TAG, recipe.display());

    }

    private void collapseHandler(){
        handler.sendEmptyMessageDelayed(0, 0);
        Log.v(TAG, "Collapse");
    }

    public Recipe getRecipe(){
        return this.recipe;
    }
}
