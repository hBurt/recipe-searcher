package com.example.recipesearch.ui.recipe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.recipesearch.R;
import com.example.recipesearch.api.APICore;
import com.example.recipesearch.api.BackgroundRequest;
import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.ui.CustomRecipes.CustomStorage;

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
        Button Rand = view.findViewById(R.id.ClearBTN);
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
                APICore api = new APICore();
                api.startRequest("", BackgroundRequest.SearchType.NEXT, getActivity().getApplicationContext(), hk);
            }
        });
        // the rand recipe is a wip
        Rand.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CustomStorage Cs = new CustomStorage(getActivity().getApplicationContext());
                Cs.Clear();
                Toast.makeText(getActivity().getApplicationContext(), "Saved custom recipes have been deleted",Toast.LENGTH_SHORT).show();
            }
        });
        cRecipe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CustomStorage Cs = new CustomStorage(getActivity().getApplicationContext());
                if (Cs.getCount() > 0)
                {
                if (RecipeActivity.getIsOpen() == false)
                {
                    Intent in = new Intent(getActivity().getApplicationContext(), RecipeActivity.class);
                    startActivity(in);
                }
                RecipeActivity.setRecipeName("Next Test"); // temp test
                Recipe_Directions_Tab_Fragment.setDirections("Next Text");
                Recipe_Ingredient_Tab_Fragment.setIngredients("Next Text");
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
                        Toast.makeText(getActivity().getApplicationContext(), "Recipe: " +Cs.getNum()+" of: "+Cs.getCount(),Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (RecipeActivity.getIsOpen())
                        {
                            RecipeActivity.setRecipeName("End of List");
                            ((RecipeActivity) getActivity()).refresh();
                        }
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
    private void askToDeleteAll() {
        final CharSequence[] options = { "Yes", "NO" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
        builder.setTitle("This will delete all saved custom recipes, are you sure about this?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Yes"))
                {
                    if (RecipeActivity.getIsOpen() == true)
                    {
                        recipeActivity.destroy();
                    }
                    CustomStorage Cs = new CustomStorage(getActivity().getApplicationContext());
                    Cs.Clear();
                    Toast.makeText(getActivity().getApplicationContext(), "Saved custom recipes have been deleted",Toast.LENGTH_SHORT).show();
                }
                else if (options[item].equals("No")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
