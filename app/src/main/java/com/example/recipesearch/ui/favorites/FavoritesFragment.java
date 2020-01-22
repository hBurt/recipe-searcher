package com.example.recipesearch.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.Random;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;

    DatabaseHelper databaseHelper;

    @Override
    public void onResume() {
        super.onResume();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        databaseHelper = ((MainActivity) getActivity()).getDatabaseHelper();

        ListView list = root.findViewById(R.id.list);
        ArrayList<Favorite> favoritesList = new ArrayList<>();//databaseHelper.getCurrentUser().getFavorites();


        populateListWithHardcodedItems(favoritesList);

        CustomAdapter customAdapter = new CustomAdapter(getContext(), favoritesList);
        list.setAdapter(customAdapter);

        return root;
    }

    private void populateListWithHardcodedItems(ArrayList<Favorite> list){

        Recipe thaiSweetPotato = new Recipe();
        thaiSweetPotato.setTitle("Thai Sweet Potato");
        thaiSweetPotato.setImageURL("https://spoonacular.com/recipeImages/thai-sweet-potato-veggie-burgers-with-spicy-peanut-sauce-262682.jpg");

        Recipe cajunSpiced = new Recipe();
        cajunSpiced.setTitle("Cajun Spiced Black Bean and Sweet Potato Burgers");
        cajunSpiced.setImageURL("https://spoonacular.com/recipeImages/Cajun-Spiced-Black-Bean-and-Sweet-Potato-Burgers-227961.jpg");

        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
        list.add(new Favorite(randomNumBetween(1, 5), thaiSweetPotato));
        list.add(new Favorite(randomNumBetween(1, 5), cajunSpiced));
    }

    private int randomNumBetween(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}