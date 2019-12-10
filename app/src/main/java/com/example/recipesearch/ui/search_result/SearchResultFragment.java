package com.example.recipesearch.ui.search_result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.R;

public class SearchResultFragment extends Fragment {

    private SearchResultViewModel searchResultViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        searchResultViewModel =
                ViewModelProviders.of(this).get(SearchResultViewModel.class);


        View root = inflater.inflate(R.layout.fragmnt_search_results, container, false);
        return root;
    }

    public void setSearchText(CharSequence c){

        EditText editText = getActivity().findViewById(R.id.editText1);

        editText.setText(c, TextView.BufferType.EDITABLE);
    }



}
