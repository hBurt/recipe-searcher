package com.example.recipesearch.ui.favorites;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.example.recipesearch.database.User;
import com.example.recipesearch.helpers.DatabaseHelper;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;

    DatabaseHelper databaseHelper;
    private EditText searchText;

    @Override
    public void onResume() {
        super.onResume();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        searchText = root.findViewById(R.id.favorites_search_text);
        databaseHelper = ((MainActivity) getActivity()).getDatabaseHelper();

        initFavoritesList(root);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return root;
    }

    private void initFavoritesList(View root){
        ListView list = root.findViewById(R.id.list);
        ArrayList<Favorite> favoritesList = databaseHelper.getCurrentUser().getFavorites();

        if(favoritesList.size() > 0){
            CustomAdapter customAdapter = new CustomAdapter(getContext(), favoritesList);
            list.setAdapter(customAdapter);
        }
    }

    private void updateList(String searchValue){

    }

}