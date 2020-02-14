package com.recipe.recipesearch.ui.recipe;

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
import com.recipe.recipesearch.ui.APIComunication.Next_recipe;
import com.recipe.recipesearch.ui.APIComunication.Random_Recipe;
import com.recipe.recipesearch.ui.CustomRecipes.CustomStorage;


public class Recipe_Similar_Recipes_Tab_Fragment extends Fragment
{
    private static String id = null;
    private static Handler h;
    private static int offSet = 1;
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
        Button cRecipe = view.findViewById(R.id.CRecipes);
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
                RecipeActivity.setRecipeName("Next Test"); // temp test
                Recipe_Directions_Tab_Fragment.setDirections("Next Text");
                Recipe_Ingredient_Tab_Fragment.setIngredients("Next Text");
                h.sendEmptyMessageDelayed(0, 1200);
            }
        });
        cRecipe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RecipeActivity.setRecipeName("Next Test"); // temp test
                Recipe_Directions_Tab_Fragment.setDirections("Next Text");
                Recipe_Ingredient_Tab_Fragment.setIngredients("Next Text");
                CustomStorage Cs = new CustomStorage(getActivity().getApplicationContext());
                if (Cs.getCount() > 0)
                {
                    if (Cs.getCName() != null)
                    {
                        RecipeActivity.setRecipeName(Cs.getCName());
                        RecipeActivity.setTime(Cs.getCTime());
                        if (Cs.getBool() == true)
                            RecipeActivity.setTakenPio(Cs.getCImgURL());
                        else
                            RecipeActivity.setPicUri(Cs.getCImgURL());
                        Recipe_Directions_Tab_Fragment.setDirections(Cs.getCDirections());
                        Recipe_Ingredient_Tab_Fragment.setIngredients(Cs.getCIngred());
                        Cs.setNum();
                        h.sendEmptyMessageDelayed(0, 300);
                    }
                }

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
