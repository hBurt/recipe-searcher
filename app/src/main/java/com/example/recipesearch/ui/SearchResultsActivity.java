package com.example.recipesearch.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class SearchResultsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        // Get the intent, verify the action and get the query
        Intent intent = null;//getIntent(); //null for now
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
}
