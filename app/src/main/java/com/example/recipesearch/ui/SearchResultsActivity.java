package com.example.recipesearch.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.example.recipesearch.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SearchResultsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra(SearchManager.QUERY);
            preformsearch(query); //will be used to well preform the search
        }
    }

    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search data
        }
    }
    private String preformsearch(String query)
    {

        String result = "Food" ; // temp val, will return the result from a search
        return result;
    }
    public boolean onSearchRequested() {
        Bundle appData = new Bundle();
        appData.putBoolean(SearchResultsActivity.INPUT_SERVICE, true);
        startSearch(null, false, appData, false);
        return true;
    }
    private  void APISearch(String query)
    {
        OkHttpClient client = new OkHttpClient();
        String Result = null;
        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?diet=vegetarian&excludeIngredients=coconut&intolerances=egg%252C%20gluten&number=10&offset=0&type=main%20course&query="+query)
                .get()
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  /*  public boolean onCreateOptionsMenu(Menu menu) { // only for use after the settings menu is made 
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.settings_menu).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }*/
}
