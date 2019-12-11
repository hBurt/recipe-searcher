package com.example.recipesearch.ui.search_result;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipesearch.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class SearchActivity extends AppCompatActivity
{
    private static String[] Suggestion = new String[]{
            "Apple","Burger","Apple Pie"
    };
    private  MaterialSearchView FsearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmnt_search_results);
        //Toolbar tool = findViewById(R.id.tool);
        //FsearchView = findViewById(R.id.Search_Query);
        FsearchView .setSuggestions(Suggestion);
        //ListView list = findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Suggestion);
        FsearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown()
            {

            }

            @Override
            public void onSearchViewClosed()
            {
                arrayAdapt.clear();
                arrayAdapt.addAll(Suggestion);
                arrayAdapt.notifyDataSetChanged();
            }
        });

        FsearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
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
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menue_search, menu);
        MenuItem menuThing = menu.findItem(R.id.searchMenu);
        FsearchView.setMenuItem(menuThing);
        return super.onCreateOptionsMenu(menu);
    }
}
