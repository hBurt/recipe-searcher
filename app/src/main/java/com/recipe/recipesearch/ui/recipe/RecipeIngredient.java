package com.recipe.recipesearch.ui.recipe;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.recipe.recipesearch.R;
import com.recipe.recipesearch.database.Recipe;

public class RecipeIngredient extends Fragment {

    private ListView list;
    private Recipe recipe;
    private IngredientAdapter ingredientAdapter;

    public RecipeIngredient(Recipe recipe) {
        setRecipe(recipe);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_recipe_ingredient, container, false);

        initList(root);
        return root;
    }

    private void initList(View root){
        list = root.findViewById(R.id.list_ingredients);

        if(getRecipe() != null && getRecipe().getIngredients().size() > 1) {
            ingredientAdapter = new IngredientAdapter(getContext(), getRecipe());
            list.setAdapter(ingredientAdapter);
        }
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
