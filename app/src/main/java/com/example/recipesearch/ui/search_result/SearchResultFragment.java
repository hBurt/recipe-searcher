package com.example.recipesearch.ui.search_result;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;

public class SearchResultFragment extends Fragment {

    private SearchResultViewModel searchResultViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        searchResultViewModel =
                ViewModelProviders.of(this).get(SearchResultViewModel.class);


        View root = inflater.inflate(R.layout.fragmnt_search_results, container, false);

        final EditText et = root.findViewById(R.id.editText1);
        MainActivity m = (MainActivity) getActivity();
        if (m != null) {
            et.setText(m.getMessage(), TextView.BufferType.EDITABLE);
        }

        et.requestFocus();
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);

        if (m != null) {
            showKeyboard(et, m.getApplicationContext());
        }

        return root;
    }

    private static void showKeyboard(EditText mEtSearch, Context context) {
        mEtSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }
}
