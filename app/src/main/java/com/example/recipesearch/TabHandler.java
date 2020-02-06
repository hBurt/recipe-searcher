package com.example.recipesearch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.recipesearch.favorite_recipe_view_ingredient;

public class TabHandler extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    String ingredients, directions;

    //Constructor to the class
    public TabHandler(FragmentManager fm, int tabCount, String ingredients, String directions) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                favorite_recipe_view_ingredient ingredient = new favorite_recipe_view_ingredient(ingredients);
                return ingredient;
            case 1:
                favorite_recipe_view_ingredient direction = new favorite_recipe_view_ingredient(directions);
                return direction;
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
