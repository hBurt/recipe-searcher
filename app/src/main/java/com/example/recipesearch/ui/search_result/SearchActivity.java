package com.example.recipesearch.ui.search_result;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesearch.ui.SearchSettingsActivity;

import com.example.recipesearch.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SearchActivity extends AppCompatActivity
{
    private SearchResultViewModel searchResultViewModel;
    SearchSettingsActivity search = new SearchSettingsActivity(); // for use with passing a query to search
    private static String[] Suggestion = new String[]{
            // just examples, can be changed for some other recommendations if desired, more can also be added
            "Steak","Burger","Apple Pie", "Baked Cod"
    };
    private HashMap<String, String> createFood(String key, String name)
    {
        HashMap<String, String> Food = new HashMap<String, String>();
        Food.put(key, name);

        return Food;
    }
    List<Map<String, String>> FoodList = new ArrayList<Map<String,String>>();
    String[] CreatedList = new String[5];
    private SearchView FsearchView;
    SimpleAdapter silAd = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);
        Toolbar tool = findViewById(R.id.ToolBox2);
        FsearchView = findViewById(R.id.search_box);
        final ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, Suggestion);
        ListView list = findViewById(R.id.listView);// list view use and creation of the adapter for it

        FsearchView.setOnSearchClickListener(new SearchView.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
            public void onSearchViewShown()
            {

            }
            public void onSearchViewClosed()
            {
                arrayAdapt.clear();
                arrayAdapt.addAll(Suggestion);
                arrayAdapt.notifyDataSetChanged();
            }
        });

        FsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                CreatedList = search.DoSearch(query);// will send the query when it is submitted to search
                FoodList.add(createFood("Food", CreatedList[0]));
                FoodList.add(createFood("Food", CreatedList[1]));
                FoodList.add(createFood("Food", CreatedList[2]));
                FoodList.add(createFood("Food", CreatedList[3]));
                FoodList.add(createFood("Food", CreatedList[4]));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if (arrayAdapt.getCount() > 0)
                arrayAdapt.clear();
                if(newText != null && !newText.isEmpty())
                {
                    for (String s : Suggestion)
                    {
                        if (s.toLowerCase().contains(newText))
                        {
                            arrayAdapt.add(s);
                        }
                        else
                            arrayAdapt.addAll(Suggestion);
                    }

                }
                arrayAdapt.notifyDataSetChanged();
                return false;
            }

        });
        // The View id used to show the data. The key number and the view id must match
        silAd = new SimpleAdapter(this, FoodList, R.layout.fragmnt_search_results,
                new String[] {"Food"}, new int[] {android.R.id.text1});
        list.setAdapter(silAd);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView clickedView = (TextView) view;
                Toast.makeText(SearchActivity.this, "Item with id ["+id+"] - Position ["+position+"] " +
                        "- Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        FsearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
