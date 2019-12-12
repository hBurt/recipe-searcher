package com.example.recipesearch.ui.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesearch.ui.SearchSettingsActivity;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.recipe.RecipeFragment;
import com.example.recipesearch.ui.recipe.RecipeViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SearchActivity extends AppCompatActivity
{
    private SearchResultViewModel searchResultViewModel;
    SearchSettingsActivity search = new SearchSettingsActivity(); // for use with passing a query to search
    private String[] CreatedList = new String[5];
    private SearchView FsearchView;
    private ArrayAdapter silAd;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);
        FsearchView = findViewById(R.id.search_box);
        ListView list = findViewById(R.id.listView);// list view use and creation of the adapter for iz
        ArrayList<String> foodArray = new ArrayList<>();
        foodArray.addAll(Arrays.asList(getResources().getStringArray(R.array.food_stuff)));
        silAd = new ArrayAdapter<String>(
                SearchActivity.this, android.R.layout.simple_expandable_list_item_1, foodArray);
        list.setAdapter(silAd);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_search, menu);
        MenuItem that = menu.findItem(R.id.searchFood);
        FsearchView = (SearchView) that.getActionView();
        FsearchView.setSubmitButtonEnabled(true);
        FsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                silAd.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
