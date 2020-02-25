package com.recipe.recipesearch.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.recipe.recipesearch.R;


public class Recipe_Directions_Tab_Fragment extends Fragment
{
    static String newText = " ";
    public Recipe_Directions_Tab_Fragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe__directions__tab_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        TextView directions = view.findViewById(R.id.Directions);
        // test and example text
            directions.setText(newText.trim().replace(".","\n"));
    }
    public static void setDirections(String string)
    {
        newText = string;
    }

    public static String getDirections() {
        return newText;
    }
}
