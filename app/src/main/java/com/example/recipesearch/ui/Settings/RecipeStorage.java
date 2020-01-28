package com.example.recipesearch.ui.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.recipesearch.ui.recipe.Recipe_Directions_Tab_Fragment;
import com.example.recipesearch.ui.recipe.Recipe_Ingredient_Tab_Fragment;
import com.example.recipesearch.ui.search_result.SearchActivity;

public class RecipeStorage extends Activity
{
    static SharedPreferences mPrefs;
    SharedPreferences.Editor edit;
    public RecipeStorage(Context context)
    {
        mPrefs = context.getSharedPreferences("Recipe_Book", MODE_PRIVATE);
    }
    public void setDirections ()
    {
        edit = mPrefs.edit();
        edit.putString(SearchActivity.getSearchedFood()+"Directions", Recipe_Directions_Tab_Fragment.getDirections());
        edit.apply();
    }
    public void setIngred()
    {
        edit = mPrefs.edit();
        edit.putString(SearchActivity.getSearchedFood()+"Ingredients", Recipe_Ingredient_Tab_Fragment.getIngredients());
        edit.apply();
    }
    public void setRecipeName(String string)
    {
        edit = mPrefs.edit();
        edit.putString(SearchActivity.getSearchedFood()+"Name", string);
        edit.apply();
    }
    public void setTimeAmount(String string)
    {
        edit = mPrefs.edit();
        edit.putString(SearchActivity.getSearchedFood()+"Time", string);
        edit.apply();
    }
    public void setImgURL(String string)
    {
        edit = mPrefs.edit();
        edit.putString(SearchActivity.getSearchedFood()+"img", string);
        edit.apply();
    }
    public void setRecipeID(String string)
    {
        edit = mPrefs.edit();
        edit.putString(SearchActivity.getSearchedFood()+"id", string);
        edit.apply();
    }
    public String getOldDirections()
    {
        if (mPrefs.contains(SearchActivity.getSearchedFood()+"Directions"))
        return mPrefs.getString(SearchActivity.getSearchedFood()+"Directions", " ");
        else
            return null;
    }
    public String getOldIngred()
    {
        if (mPrefs.contains(SearchActivity.getSearchedFood()+"Ingredients"))
        return mPrefs.getString(SearchActivity.getSearchedFood()+"Ingredients", " ");
        else
            return null;
    }
    public String getOldName()
    {
        if(mPrefs.contains(SearchActivity.getSearchedFood()+"Name"))
        return mPrefs.getString(SearchActivity.getSearchedFood()+"Name", " ");
        else
            return null;
    }
    public String getOldTime()
    {
        if(mPrefs.contains(SearchActivity.getSearchedFood()+"Time"))
        return mPrefs.getString(SearchActivity.getSearchedFood()+"Time", " ");
        else
            return null;
    }
    public String getOldImgURL()
    {
        if(mPrefs.contains(SearchActivity.getSearchedFood()+"img"))
        return mPrefs.getString(SearchActivity.getSearchedFood()+"img", " ");
        else
            return null;
    }
    public String getOldID()
    {
        if(mPrefs.contains(SearchActivity.getSearchedFood()+"id"))
        return mPrefs.getString(SearchActivity.getSearchedFood()+"id", " ");
        else
            return null;
    }
    public static boolean isThisInTHeBook()
    {
        if(mPrefs.contains(SearchActivity.getSearchedFood()+"id"))
        {
            return true;
        }
        else
            return false;
    }
}