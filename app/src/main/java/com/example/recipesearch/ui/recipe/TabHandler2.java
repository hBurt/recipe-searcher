package com.example.recipesearch.ui.recipe;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.recipesearch.database.Recipe;

public class TabHandler2 extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Ingredient ingredients;
    Recipe recipe;
    private RecipeActivityV2 recipeActivityV2s;

    //Constructor to the class
    public TabHandler2(FragmentManager fm, int tabCount, Recipe recipe, RecipeActivityV2 recipeActivityV2) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.recipe = recipe;
        this.recipeActivityV2s = recipeActivityV2;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                RecipeIngredient ingredient = new RecipeIngredient(recipe);
                return ingredient;
            case 1:
                RecipeInstruction instruction = new RecipeInstruction(recipe);
                return instruction;
            case 2:
               /* favorite_recipe_view_ingredient direction2 = new favorite_recipe_view_ingredient(" Ingredients here"); //directions
                return direction2;*/
                Recipe_Similar_Recipes_Tab_Fragment Similar_Recipes = new Recipe_Similar_Recipes_Tab_Fragment(recipeActivityV2s);
                return Similar_Recipes;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
