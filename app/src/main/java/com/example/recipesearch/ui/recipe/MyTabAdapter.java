package com.example.recipesearch.ui.recipe;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.recipesearch.database.Recipe;

class MyTabAdapter extends FragmentPagerAdapter
{
    Context context;
    int totalTabs;
    private RecipeActivityV2 rec;

    Recipe recipe;
    public MyTabAdapter(Context c, FragmentManager fm, int totalTabs)
    {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
        rec = new RecipeActivityV2();
    }
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                Recipe_Ingredient_Tab_Fragment ingredient = new Recipe_Ingredient_Tab_Fragment();
                return ingredient;
            case 1:
                Recipe_Directions_Tab_Fragment Directions = new Recipe_Directions_Tab_Fragment();
                return Directions;
            case 2:
                Recipe_Similar_Recipes_Tab_Fragment Similar_Recipes = new Recipe_Similar_Recipes_Tab_Fragment( rec);
                return Similar_Recipes;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}