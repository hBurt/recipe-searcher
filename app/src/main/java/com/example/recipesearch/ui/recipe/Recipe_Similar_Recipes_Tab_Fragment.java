package com.example.recipesearch.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipesearch.R;


public class Recipe_Similar_Recipes_Tab_Fragment extends Fragment
{
    private static String id = null;
    public Recipe_Similar_Recipes_Tab_Fragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe__similar__recipes__tab_, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Button next = view.findViewById(R.id.Sim_btn);
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // when the button is pressed should get the info for the next dish
                // will eventually add a check to see if the next is a repeat
                Next_Similar_Activity nextS = new Next_Similar_Activity();
                //nextS.execute(); // calls the func to get something similar
                String id2 = Next_Similar_Activity.getNewID();
                //if (id2 != id)
                  //  id = id2;
                String test = Next_Similar_Activity.getName();
                if (test.length() > 0) // in theory should be an error check for the name
                      RecipeActivity.setRecipeName(test);
                else
                    RecipeActivity.setRecipeName("Next Test");

                ((RecipeActivity)getActivity()).refresh();
            }
        });
    }
    static public String getID()
    {
        return id;
    }
    static public void setID(String newID)
    {
        id = newID;
    }
}
