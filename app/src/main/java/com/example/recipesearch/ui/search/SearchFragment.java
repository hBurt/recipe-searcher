package com.example.recipesearch.ui.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.R;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        super.onCreate(savedInstanceState);
        // Get the intent, verify the action and get the query
        Intent intent = null;//getIntent(); //null for now
        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra(SearchManager.QUERY);
            preformsearch(query); //will be used to well preform the search
        }
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = root.findViewById(R.id.Search_Result);
        final SearchView search = root.findViewById(R.id.Search_Query);
        searchViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                textView.setText(s);
            }
        });
        return root;
    }

    private String preformsearch(String query) 
    {
        String result = "Food" ; // temp val, will return the result from a search
        return result;
    }

}
