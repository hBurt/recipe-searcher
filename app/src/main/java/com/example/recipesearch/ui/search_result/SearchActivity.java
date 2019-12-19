package com.example.recipesearch.ui.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.recipesearch.ui.SearchSettingsActivity;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.recipe.RecipeActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class SearchActivity extends AppCompatActivity
{
    private SearchView FsearchView;
    private ArrayAdapter silAd;
    private ListView list;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);
        tool = findViewById(R.id.tb);
        tool.inflateMenu(R.menu.menu_search);
        list = findViewById(R.id.listView);// list view use and creation of the adapter for iz
        ArrayList<String> foodArray = new ArrayList<>();
        foodArray.addAll(Arrays.asList(getResources().getStringArray(R.array.food_stuff)));
        silAd = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, foodArray);
        list.setAdapter(silAd);
        FsearchView = findViewById(R.id.searchFood);
        FsearchView.setQueryHint("Search Food or Ingredient");
        FsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                SearchSettingsActivity search = new SearchSettingsActivity(); // for use with passing a query to search
                search.DoSearch(query); // for searching
                Intent in = new Intent(SearchActivity.this, RecipeActivity.class);
                startActivity(in);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                silAd.getFilter().filter(newText);
                return false;
            }
        });
    }
}
