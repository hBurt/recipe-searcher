package com.recipe.recipesearch.ui.recipe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipesearch.R;


public class Recipe_Ingredient_Tab_Fragment extends Fragment
{
    static String newText = " ";
    String sampleText = "worcestershire sauce\n" + "green onions\n" + "spring-onions\n" + "mushrooms\n" + "olive oil\n" + "seasoning of choice\n" + "butter\n" + "garlic\n" + "pepper\n" + "all purpose flour\n";
    public Recipe_Ingredient_Tab_Fragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe__ingredient__tab_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        TextView ingredient = view.findViewById(R.id.Ingredients);
        // test and example text
        if (newText.length() > 2)
            ingredient.setText(newText);
        else
            ingredient.setText(sampleText);
    }
    public static void setIngredients(String string)
    {
        newText = string;
    }

    public static String getIngredients() {
        return newText;
    }
}
