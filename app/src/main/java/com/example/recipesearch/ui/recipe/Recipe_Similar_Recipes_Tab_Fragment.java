package com.example.recipesearch.ui.recipe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipesearch.R;
import com.example.recipesearch.api.APICore;
import com.example.recipesearch.api.BackgroundRequest;
import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.ui.APIComunication.Next_Similar_Activity;
import com.example.recipesearch.ui.APIComunication.Next_recipe;
import com.example.recipesearch.ui.APIComunication.Random_Recipe;
import com.example.recipesearch.ui.CustomRecipes.CustomRecipe;
import com.example.recipesearch.ui.CustomRecipes.CustomStorage;
import com.example.recipesearch.ui.search_result.SearchActivity;

import static com.example.recipesearch.ui.recipe.RecipeActivity.recipeActivity;


public class Recipe_Similar_Recipes_Tab_Fragment extends Fragment
{
    private static String id = null;
    private static Handler h, hk;
    private static int offSet = 1;
    private Recipe recipe;
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
    @SuppressLint("HandlerLeak")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Button next = view.findViewById(R.id.Sim_btn);
       // Button Rand = view.findViewById(R.id.Random_Recipe_Btn);
        Button cRecipe = view.findViewById(R.id.CRecipes);
        h = new  Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            ((RecipeActivity)getActivity()).refresh();
            offSet = offSet + 1;
        }
    };
        hk = new  Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                ((RecipeActivityV2)getActivity()).refresh();
                offSet = offSet + 1;
            }
        };
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (RecipeActivity.getIsOpen() == true)
                {
                    recipeActivity.destroy();
                }
                APICore api = new APICore();
                api.startRequest("", BackgroundRequest.SearchType.NEXT, getActivity().getApplicationContext(), hk);
            }
        });
        // the rand recipe is a wip
        /*Rand.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (RecipeActivity.getIsOpen() == true)
                {
                    recipeActivity.destroy();
                }
                APICore api = new APICore();
                api.startRequest("", BackgroundRequest.SearchType.RANDOM, getActivity().getApplicationContext(), hk);
            }
        });*/
        cRecipe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (RecipeActivity.getIsOpen() == false)
                {
                    Intent in = new Intent(getActivity().getApplicationContext(), RecipeActivity.class);
                    startActivity(in);
                }
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
                        String ingred = Cs.getCIngred().trim() .replace(",", " \n ");
                        Recipe_Ingredient_Tab_Fragment.setIngredients(ingred);
                        Cs.setNum();
                        if (RecipeActivity.getIsOpen() == true)
                        {
                            h.sendEmptyMessageDelayed(0, 300);
                        }

                    }
                    else
                    {
                        RecipeActivity.setRecipeName("End of List");
                        ((RecipeActivity)getActivity()).refresh();
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
