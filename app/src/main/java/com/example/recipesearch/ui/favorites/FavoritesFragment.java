package com.example.recipesearch.ui.favorites;

import android.os.Bundle;
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



        return root;
    }

    private void populateListWithUserData(ArrayList<Favorite> list){
        User user = databaseHelper.getCurrentUser();

        if(user.getFavorites() != null){
            list.addAll(user.getFavorites());
        }
    }

    private void initFavoritesList(View root){
        ListView list = root.findViewById(R.id.list);
        ArrayList<Favorite> favoritesList = new ArrayList<>();//databaseHelper.getCurrentUser().getFavorites();

        populateListWithUserData(favoritesList);

        CustomAdapter customAdapter = new CustomAdapter(getContext(), favoritesList);
        list.setAdapter(customAdapter);
    }

}