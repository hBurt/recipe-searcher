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
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.ui.UiHelper;
import com.example.recipesearch.ui.search_result.SearchResultFragment;

public class HomeSearchFragment extends Fragment {

    private HomeSearchViewModel homeSearchViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeSearchViewModel =
                ViewModelProviders.of(this).get(HomeSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_search, container, false);

        final UiHelper ui = new UiHelper(getFragmentManager());

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
                MainActivity m = (MainActivity) getActivity();
                m.setMessage(s);
                ui.switchScreen(new SearchResultFragment());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }
}