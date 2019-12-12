package com.example.recipesearch.ui.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.ui.SearchSettingsActivity;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.UiHelper;
import com.example.recipesearch.ui.recipe.RecipeFragment;
import com.example.recipesearch.ui.recipe.RecipeViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SearchActivity extends AppCompatActivity
{
    SearchSettingsActivity search = new SearchSettingsActivity(); // for use with passing a query to search
    private SearchView FsearchView;
    private ArrayAdapter silAd;
    private  ListView list;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);
        tool = findViewById(R.id.tb);
        tool.inflateMenu(R.menu.menu_search);// displays the toolbar
        list = findViewById(R.id.listView);// list view use and creation of the adapter for iz
        ArrayList<String> foodArray = new ArrayList<>();
        foodArray.addAll(Arrays.asList(getResources().getStringArray(R.array.food_stuff)));
        silAd = new ArrayAdapter<String>(
                SearchActivity.this, android.R.layout.simple_list_item_1, foodArray);
        list.setAdapter(silAd);
        FsearchView = findViewById(R.id.searchFood);
        FsearchView.setQueryHint("Search Food or Ingredient");
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
    }

}
