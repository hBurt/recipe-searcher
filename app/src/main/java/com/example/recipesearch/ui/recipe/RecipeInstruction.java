package com.example.recipesearch.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.recipesearch.R;
import com.example.recipesearch.database.Recipe;

public class RecipeInstruction extends Fragment {

    private ListView list;
    private Recipe recipe;
    private InstructionsAdapter instructionsAdapter;

    public RecipeInstruction(Recipe recipe) {
        setRecipe(recipe);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_instruction, container, false);

        initList(root);
        return root;
    }

    private void initList(View root){
        list = root.findViewById(R.id.list_instructions);

        if(getRecipe() != null && getRecipe().getSteps().size() > 1) {
            instructionsAdapter = new InstructionsAdapter(getContext(), getRecipe().getSteps());
            list.setAdapter(instructionsAdapter);
        }
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}