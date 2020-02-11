package com.example.recipesearch.ui.recipe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.APIComunication.Next_Similar_Activity;
import com.example.recipesearch.ui.APIComunication.Next_recipe;
import com.example.recipesearch.ui.APIComunication.Random_Recipe;
import com.example.recipesearch.ui.search_result.SearchActivity;


public class Recipe_Similar_Recipes_Tab_Fragment extends Fragment
{
    private static String id = null;
    static Handler h;
    static int offSet = 1;
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
        Button Rand = view.findViewById(R.id.Random_Recipe_Btn);
        h = new  Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //((RecipeActivity)getActivity()).refresh();
                offSet = offSet + 1;
            }
        };
        //wip
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // when the button is pressed should get the info for the next dish
                // will eventually add a check to see if the next is a repeat
                Next_recipe nextS = new Next_recipe();
                nextS.execute(); // calls the func to get something similar
                RecipeActivity.setRecipeName("Next Test"); // temp test
                String direct = " ";
                Recipe_Directions_Tab_Fragment.setDirections("Next Text");
                String ingredient = " ";
                Recipe_Ingredient_Tab_Fragment.setIngredients("Next Text");
                h.sendEmptyMessageDelayed(0, 1200);
            }
        });
        // the rand recipe is a wip
        Rand.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Random_Recipe nRand = new Random_Recipe();
                nRand.execute();
                /* String test = Next_Similar_Activity.getName();
                if (test.length() > 0) // in theory should be an error check for the name
                      RecipeActivity.setRecipeName(test);
                else*/
                RecipeActivity.setRecipeName("Next Test"); // temp test
                String direct = " ";
                Recipe_Directions_Tab_Fragment.setDirections("Next Text");
                String ingredient = " ";
                Recipe_Ingredient_Tab_Fragment.setIngredients("Next Text");
                h.sendEmptyMessageDelayed(0, 1200);
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
    public static int getOffset(){return offSet;}
}
