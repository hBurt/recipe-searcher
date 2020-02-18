package com.example.recipesearch.api;

import android.util.Log;

import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.ui.recipe.Recipe_Similar_Recipes_Tab_Fragment;

public class URLHandler {

    private static final String TAG = "URLHandler";

    String amount = "&number=1";

    String baseSearch = "https://api.spoonacular.com/recipes/";
    String byRecipe = "search?query=";
    String byIngredients = "findByIngredients?ingredients=";
    String apiKeyBase = "&apiKey=";
    String apiKey = "829c5610db454ca091cbd571f9cbbf61";
    String apiKey2 = "165a81b84213461a8702d3ec687eacb6";
    String apiKey3 = "46953957ae604aeba07e605696eef0cc";
    String returned = "";
    String randomSearch = "random?number=1";
    String analyze = "analyzedInstructions?stepBreakdown=true";

    String ingredientWidget = "ingredientWidget.json?";

    private String query;
    private BackgroundRequest.SearchType searchType;

    public URLHandler(String query, BackgroundRequest.SearchType searchType) {
        setQuery(query);
        setSearchType(searchType);
    }

    public String buildUrl() {

        if (getSearchType() == BackgroundRequest.SearchType.RECIPE) {

            returned = baseSearch +
                    byRecipe +
                    getQuery() +
                    amount +
                    apiKeyBase + apiKey3;


        }
        else if(getSearchType() == BackgroundRequest.SearchType.RANDOM)
        {
            returned = baseSearch +
                    randomSearch +
                    apiKeyBase + apiKey3;
        }
        else if(getSearchType() == BackgroundRequest.SearchType.NEXT)
        {
            returned = buildUrlForSimilar(Recipe.getID());
        }
        else if (getSearchType() == BackgroundRequest.SearchType.INGREDIENT) {

            StringBuilder recreated = new StringBuilder();

            String[] ingredients = splitStringByComma();

            for (int i = 0; i < ingredients.length; i++) {
                if (i < ingredients.length - 1) {
                    recreated.append(ingredients[i]).append(",+");
                } else {
                    recreated.append(ingredients[i]);
                }
            }

            returned = baseSearch +
                    byIngredients +
                    recreated +
                    amount +
                    apiKeyBase + apiKey3;
        }
        Log.v(TAG, returned);

        return returned;
    }

    public String buildUrlForIngredients(int id){

        // https://api.spoonacular.com/recipes/####/ingredientWidget.json?apiKey=829c5610db454ca091cbd571f9cbbf61

        String out = baseSearch +
                id + "/" +
                ingredientWidget +
                "apiKey=" + apiKey3;

        Log.v(TAG, "[buildUrlForIngredients]" + out);
        return out;
    }

    public String buildUrlForInstructions(int id) {

        //https://api.spoonacular.com/recipes/492560/analyzedInstructions?stepBreakdown=true&apiKey=829c5610db454ca091cbd571f9cbbf61
        //https://api.spoonacular.com/recipes/###/analyzedInstructions?stepBreakdown=true&apiKey=829c5610db454ca091cbd571f9cbbf61

        String out = baseSearch +
                id + "/" +
                analyze +
                apiKeyBase + apiKey3;

        Log.v(TAG, "[buildUrlForInstructions]" + out);
        return out;
    }

    public String buildUrlForSimilar(int id){

        return baseSearch +
                Recipe_Similar_Recipes_Tab_Fragment.getBaseID() + "/" +
                "similar" +
                apiKeyBase + apiKey3;
    }

    private String[] splitStringByComma() {

        return getQuery().split(",");
    }

    public BackgroundRequest.SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(BackgroundRequest.SearchType searchType) {
        this.searchType = searchType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        if(query != null && query.contains(" ")){
            this.query = query.replaceAll("\\s+","%20");
        } else {
            this.query = query;
        }
    }
}


