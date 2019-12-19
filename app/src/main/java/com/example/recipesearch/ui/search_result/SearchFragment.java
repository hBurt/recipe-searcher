package com.example.recipesearch.ui.search_result;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.R;

public class SearchFragment extends Fragment {

    private SearchViewModel searchResultViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        searchResultViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);

        View root = inflater.inflate(R.layout.fragmnt_search_results, container, false);

       /* final SearchView et = root.findViewById(R.id.SearchBox);
        MainActivity m = (MainActivity) getActivity();

        if (m != null)
        {
            et.setQuery(m.getMessage(),false);
        }

        et.setFocusable(true);
        et.setFocusableInTouchMode(true);

        if (m != null)
        {
            showKeyboard(et, m.getApplicationContext());
        }*/

        return root;
    }

    private static void showKeyboard(SearchView editText, Context context) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }
}
