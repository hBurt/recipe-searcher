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
import com.example.recipesearch.database.User;
import com.example.recipesearch.ui.CustomRecipes.CustomStorage;
import com.example.recipesearch.ui.search_result.SearchingActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.example.recipesearch.ui.recipe.RecipeActivity.recipeActivity;


public class Recipe_Similar_Recipes_Tab_Fragment extends Fragment
{
    private static String id = null;
    private static String baseID = null;
    private static Handler h, hk, check, R2;
    private static int offSet = 1;
    private boolean clicks = false;
    private static Recipe recipe;
    private RecipeActivityV2 REA2;
    User user;
    public Recipe_Similar_Recipes_Tab_Fragment( RecipeActivityV2 RA2)
    {
        // Required empty public constructor
        REA2 = RA2;
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
        clicks = false;
        super.onViewCreated(view, savedInstanceState);
        Button next = view.findViewById(R.id.Sim_btn);
        Button Clear = view.findViewById(R.id.ClearBTN);
        Button cRecipe = view.findViewById(R.id.CRecipes);
        h = new  Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            ((RecipeActivity)getActivity()).refresh();
        }
    };
        hk = new  Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                Intent se = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), SearchingActivity.class);
                startActivity(se);
            }
        };
        R2 = new  Handler()
        {
            @Override
            public void handleMessage(@NotNull Message msg)
            {
                REA2.setRecipe(recipe);
                RecipeActivityV2.setUseSimilar();
                ((RecipeActivityV2) Objects.requireNonNull(getActivity())).refresh();
                Toast.makeText(getActivity().getApplicationContext(), "Next Similar Recipe has been loaded",Toast.LENGTH_SHORT).show();
            }
        };
        check = new  Handler()
        {
            @Override
            public void handleMessage(@NotNull Message msg)
            {
                clicks = false;
            }
        };
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!RecipeActivity.getIsOpen())
                {
                    APICore api = new APICore();
                    api.startRequest(String.valueOf(Recipe.getID()), BackgroundRequest.SearchType.NEXT, getActivity().getApplicationContext(), R2);
                    Intent se = new Intent(getActivity(), SearchingActivity.class);
                    startActivity(se);
                }
                if (RecipeActivity.getIsOpen())
                {
                    Toast.makeText(getActivity().getApplicationContext(), "This function is not available while viewing Custom Recipes",Toast.LENGTH_LONG).show();
                }
            }
        });
        // the rand recipe is a wip
        Clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!clicks)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Press button again to delete all recipes",Toast.LENGTH_LONG).show();
                    clicks = true;
                    check.sendEmptyMessageDelayed(0, 4000);
                }
                else
                {
                    CustomStorage Cs = new CustomStorage(getActivity().getApplicationContext());
                    Cs.Clear();
                    Toast.makeText(getActivity().getApplicationContext(), "Saved custom recipes have been deleted",Toast.LENGTH_LONG).show();
                    clicks = false;
                }
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

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        clicks = false;
    }
    public static String getBaseID(){return baseID;}
    public static void setBaseID(String id){ baseID = id;}
    public static void setRecipe(Recipe nRecipe){recipe = nRecipe; }
}
