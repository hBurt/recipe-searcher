package com.example.recipesearch.api;

import android.util.Log;

import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.ui.recipe.Recipe_Similar_Recipes_Tab_Fragment;
import com.example.recipesearch.ui.search_result.SearchActivity;

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
    String apiKey4 = "db8b4af6c4224c28a2f47fa262d8cf13";
    String apiKey5 = "14676380de0c4536ac53764af2e2cde0";
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
                    apiKeyBase + apiKey5;


        }
        else if(getSearchType() == BackgroundRequest.SearchType.RANDOM)
        {
            returned = baseSearch +
                    randomSearch +
                    apiKeyBase + apiKey5;
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
                    apiKeyBase + apiKey5;
        }
        Log.v(TAG, returned);

        return returned;
    }

    public String buildUrlForIngredients(int id){

        // https://api.spoonacular.com/recipes/####/ingredientWidget.json?apiKey=829c5610db454ca091cbd571f9cbbf61

        String out = baseSearch +
                id + "/" +
                ingredientWidget +
                "apiKey=" + apiKey5;

        Log.v(TAG, "[buildUrlForIngredients]" + out);
        return out;
    }

    public String buildUrlForInstructions(int id) {

        //https://api.spoonacular.com/recipes/492560/analyzedInstructions?stepBreakdown=true&apiKey=829c5610db454ca091cbd571f9cbbf61
        //https://api.spoonacular.com/recipes/###/analyzedInstructions?stepBreakdown=true&apiKey=829c5610db454ca091cbd571f9cbbf61

        String out = baseSearch +
                id + "/" +
                analyze +
                apiKeyBase + apiKey5;

        Log.v(TAG, "[buildUrlForInstructions]" + out);
        return out;
    }

    public String buildUrlForSimilar(int id){
        String prevID = null;
        if( Recipe_Similar_Recipes_Tab_Fragment.getBaseID() != null)
        prevID = Recipe_Similar_Recipes_Tab_Fragment.getBaseID();
        else
        prevID = SearchActivity.getPreviousID();
        return baseSearch +
                prevID + "/" +
                "similar" + "?number=1"+
                apiKeyBase + apiKey5;
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


