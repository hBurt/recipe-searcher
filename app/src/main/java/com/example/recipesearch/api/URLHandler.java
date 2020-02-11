package com.example.recipesearch.api;

import android.util.Log;

public class URLHandler {

    private static final String TAG = "URLHandler";

    String amount = "&number=1";

    String baseSearch = "https://api.spoonacular.com/recipes/";
    String byRecipe = "search?query=";
    String byIngredients = "findByIngredients?ingredients=";
    String apiKeyBase = "&apiKey=";
    String apiKey = "829c5610db454ca091cbd571f9cbbf61";
    String apiKey2 = "165a81b84213461a8702d3ec687eacb6";
    String returned = "";

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
                    apiKeyBase + apiKey2;


        } else if (getSearchType() == BackgroundRequest.SearchType.INGREDIENT) {

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
                    apiKeyBase + apiKey2;
        }

        Log.v(TAG, returned);

        return returned;
    }

    public String buildUrlForIngredients(int id){

        // https://api.spoonacular.com/recipes/####/ingredientWidget.json?apiKey=829c5610db454ca091cbd571f9cbbf61

        String out = baseSearch +
                id + "/" +
                ingredientWidget +
                "apiKey=" + apiKey2;

        Log.v(TAG, "[buildUrlForIngredients]" + out);
        return out;
    }

    public String buildUrlForInstructions(int id) {

        //https://api.spoonacular.com/recipes/492560/analyzedInstructions?stepBreakdown=true&apiKey=829c5610db454ca091cbd571f9cbbf61
        //https://api.spoonacular.com/recipes/###/analyzedInstructions?stepBreakdown=true&apiKey=829c5610db454ca091cbd571f9cbbf61

        String out = baseSearch +
                id + "/" +
                analyze +
                apiKeyBase + apiKey2;

        Log.v(TAG, "[buildUrlForInstructions]" + out);
        return out;
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


