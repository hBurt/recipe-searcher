package com.recipe.recipesearch.ui.recipe;

import android.content.Context;
import android.content.SharedPreferences;

import com.recipe.recipesearch.ui.search_result.SearchActivity;

import static android.content.Context.MODE_PRIVATE;

public class RecipeStorage
{
    static SharedPreferences mPrefs;
    static SharedPreferences plannedPrefs;
    static SharedPreferences starting;
    SharedPreferences.Editor edit;
    public RecipeStorage(Context context)
    {
        mPrefs = context.getSharedPreferences("Recipe_Book", MODE_PRIVATE);
        plannedPrefs =  context.getSharedPreferences("Day_Plan", MODE_PRIVATE);
        starting =  context.getSharedPreferences("OGName", MODE_PRIVATE);
    }
    public void setOGName(String s)
    {
        edit = starting.edit();
        if (starting.contains("OGName")) { }
        else
        edit.putString("OGName",s );
        edit.apply();
    }
    public String getOGName()
    {
        if (starting.contains("OGName"))
            return starting.getString("OGName", " ");
        else
            return null;
    }
    public void clearOG()
    {
        starting.edit().remove("OGName").apply();
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
        edit.putString(SearchActivity.getSearchedFood()+"Ingredient", Recipe_Ingredient_Tab_Fragment.getIngredients());
        edit.apply();
    }
    public void setRecipeName(String string)
    {
        edit = mPrefs.edit();
        edit.putString(SearchActivity.getSearchedFood()+"Name", string);
        for (int i = 0; i < 11; i++)
        {
            if (mPrefs.contains(i+"Name")){}
            else {
                edit.putString(i + "Name", string);
                break;
            }
        }
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
        if (mPrefs.contains(SearchActivity.getSearchedFood()+"Ingredient"))
        return mPrefs.getString(SearchActivity.getSearchedFood()+"Ingredient", " ");
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
    public  boolean isThisInTheBook()
    {
        if(mPrefs.contains(SearchActivity.getSearchedFood()+"id"))
        {
            return true;
        }
        else
            return false;
    }
    public void removePref()
    {
        if (mPrefs.contains(SearchActivity.getSearchedFood()+"Name" ))
        {
            mPrefs.edit().remove(SearchActivity.getSearchedFood()+"Name").apply();
            mPrefs.edit().remove(SearchActivity.getSearchedFood()+"id").apply();
            mPrefs.edit().remove(SearchActivity.getSearchedFood()+"img").apply();
            mPrefs.edit().remove(SearchActivity.getSearchedFood()+"Ingredient").apply();
            mPrefs.edit().remove(SearchActivity.getSearchedFood()+"Directions").apply();
            mPrefs.edit().remove(SearchActivity.getSearchedFood()+"Time").apply();
        }
    }
    public void clearRecipeBook()
    {
        mPrefs.edit().clear().apply();
    }
    public void clearDayPlan()
    {
        plannedPrefs.edit().clear().apply();
    }
    public void setDayPlan(String s)
    {
        edit = plannedPrefs.edit();
        edit.putString("good_Plan", s);
        edit.apply();
    }
    public String getPlan()
    {
        if (plannedPrefs.contains("good_Plan"))
            return plannedPrefs.getString("good_Plan", " ");
        else
            return " ";
    }
    public void removeFirstPref()
    {
        String nameToRemove = " " ;
        String followingName0 = " ";
        String followingName1 = " ";
        String followingName2 = " ";
        String followingName3 = " ";
        String followingName4 = " ";
        String followingName5 = " ";
        String followingName6 = " ";
        String followingName7 = " ";
        String followingName8 = " ";
        for (int a = 0; a < 11; a++)
        {

            if (mPrefs.contains(a+"Name" ))
            {
                if (a > 9)// because i want this to happen if there are 10 or more saved sets
                {
                    nameToRemove = mPrefs.getString(0+"Name", " ");
                    followingName0= mPrefs.getString(1+"Name", " ");
                    followingName1= mPrefs.getString(2+"Name", " ");
                    followingName2= mPrefs.getString(3+"Name", " ");
                    followingName3= mPrefs.getString(4+"Name", " ");
                    followingName4= mPrefs.getString(5+"Name", " ");
                    followingName5= mPrefs.getString(6+"Name", " ");
                    followingName6= mPrefs.getString(7+"Name", " ");
                    followingName7= mPrefs.getString(8+"Name", " ");
                    followingName8= mPrefs.getString(9+"Name", " ");
                    mPrefs.edit().remove(0+"Name").apply();
                    mPrefs.edit().remove(1+"Name").apply();
                    mPrefs.edit().remove(2+"Name").apply();
                    mPrefs.edit().remove(3+"Name").apply();
                    mPrefs.edit().remove(4+"Name").apply();
                    mPrefs.edit().remove(5+"Name").apply();
                    mPrefs.edit().remove(6+"Name").apply();
                    mPrefs.edit().remove(7+"Name").apply();
                    mPrefs.edit().remove(8+"Name").apply();
                    mPrefs.edit().remove(9+"Name").apply();
                    edit = mPrefs.edit();
                    edit.putString(0+"Name", followingName0);
                    edit.putString(1+"Name", followingName1);
                    edit.putString(2+"Name", followingName2);
                    edit.putString(3+"Name", followingName3);
                    edit.putString(4+"Name", followingName4);
                    edit.putString(5+"Name", followingName5);
                    edit.putString(6+"Name", followingName6);
                    edit.putString(7+"Name", followingName7);
                    edit.putString(8+"Name", followingName8);
                    edit.apply();
                    mPrefs.edit().remove(nameToRemove+"Name").apply();
                    mPrefs.edit().remove(nameToRemove+"id").apply();
                    mPrefs.edit().remove(nameToRemove+"img").apply();
                    mPrefs.edit().remove(nameToRemove+"Ingredient").apply();
                    mPrefs.edit().remove(nameToRemove+"Directions").apply();
                    mPrefs.edit().remove(nameToRemove+"Time").apply();
                }
            }
        }

    }
}