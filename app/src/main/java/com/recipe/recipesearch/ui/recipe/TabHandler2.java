package com.recipe.recipesearch.ui.recipe;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.favorite_recipe_view_ingredient;

public class TabHandler2 extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Ingredient ingredients;
    Recipe recipe;

    //Constructor to the class
    public TabHandler2(FragmentManager fm, int tabCount, Recipe recipe) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.recipe = recipe;
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
                favorite_recipe_view_ingredient direction2 = new favorite_recipe_view_ingredient(" Ingredients here"); //directions
                return direction2;
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
