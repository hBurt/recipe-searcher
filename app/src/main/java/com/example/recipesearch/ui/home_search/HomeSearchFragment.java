package com.example.recipesearch.ui.home_search;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.search_result.SearchResultFragment;

import java.util.Objects;

public class HomeSearchFragment extends Fragment {

    private HomeSearchViewModel homeSearchViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeSearchViewModel =
                ViewModelProviders.of(this).get(HomeSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_search, container, false);

        //Do img color overlay
        ImageView iv = root.findViewById(R.id.imageView);
        iv.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

        EditText et = root.findViewById(R.id.search_bar_edit_text);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchScreen();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }

    void switchScreen(){
        SearchResultFragment newFragment = new SearchResultFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.nav_host_fragment, newFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

}