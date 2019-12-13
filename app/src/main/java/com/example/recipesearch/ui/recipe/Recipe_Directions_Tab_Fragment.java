package com.example.recipesearch.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.R;

public class Recipe_Directions_Tab_Fragment extends Fragment
{
    private RecipeViewModel recipeViewModel;
    ImageView Image ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                ViewModelProviders.of(this).get(RecipeViewModel.class);
        View root = inflater.inflate(R.layout.recipe_page, container, false);
        final TextView textView = root.findViewById(R.id.Recipe_Name);
        Image = (ImageView) Image.findViewById(R.id.Image_of_Food);

        return root;
    }


}
