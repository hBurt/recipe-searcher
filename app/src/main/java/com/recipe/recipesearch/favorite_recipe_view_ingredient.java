package com.recipe.recipesearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class favorite_recipe_view_ingredient extends Fragment {

    private TextView ingredientsView;

    private String ingredients;

    public favorite_recipe_view_ingredient(String ingredients){
        this.ingredients = ingredients;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite_recipe_view_ingredient, container, false);

        ingredientsView = root.findViewById(R.id.favorite_recipe_view_ingredients);


        //todo
        ingredientsView.setText(ingredients);

        return root;
    }

}
