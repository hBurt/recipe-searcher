package com.example.recipesearch.ui.CustomRecipes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.recipesearch.ui.recipe.Recipe_Directions_Tab_Fragment;
import com.example.recipesearch.ui.recipe.Recipe_Ingredient_Tab_Fragment;
import com.example.recipesearch.ui.search_result.SearchActivity;

import static android.content.Context.MODE_PRIVATE;

public class CustomStorage
{
    private static SharedPreferences Custom;
    private SharedPreferences.Editor edit;
    private static int num = 0;
    // will have a cap of 10 for internal
    public CustomStorage(Context context)
    {
        Custom = context.getSharedPreferences("Custom_Recipe_Book", MODE_PRIVATE);
    }
    public void setDirections (String direct)
    {
        edit = Custom.edit();
        for (int i = 0; i < 11; i++)
        {
            if (Custom.contains(i+"Directions")){}
            else {
                edit.putString(i+"Directions", direct);
                break;
            }
        }
        edit.apply();
    }
    public void setIngred(String Ingred)
    {
        edit = Custom.edit();
        for (int i = 0; i < 11; i++)
        {
            if (Custom.contains(i+"Ingredients")){}
            else {
                edit.putString(i+"Ingredients",Ingred);
                break;
            }
        }
        edit.apply();
    }
    public void setRecipeName(String string)
    {
        edit = Custom.edit();
        for (int i = 0; i < 11; i++)
        {
            if (Custom.contains(i+"CName")){}
            else {
                edit.putString(i + "CName", string);
                break;
            }
        }
        edit.apply();
    }
    public void setTimeAmount(String string)
    {
        edit = Custom.edit();
        for (int i = 0; i < 11; i++)
        {
            if (Custom.contains(i+"Time")){}
            else {
                edit.putString(i+"Time", string);
                break;
            }
        }
        edit.apply();
    }
    public void setImgURL(String string)
    {
        edit = Custom.edit();
        for (int i = 0; i < 11; i++)
        {
            if (Custom.contains(i+"img")){}
            else {
                edit.putString(i+"img", string);
                break;
            }
        }
        edit.apply();
    }
    public String getOldDirections()
    {
        if (Custom.contains("Directions"))
            return Custom.getString("Directions", " ");
        else
            return null;
    }
    public String getOldIngred()
    {
        if (Custom.contains("Ingredients"))
            return Custom.getString("Ingredients", " ");
        else
            return null;
    }
    public String getOldName()
    {
        if(Custom.contains("CName"))
            return Custom.getString("CName", " ");
        else
            return null;
    }
    public String getOldTime()
    {
        if(Custom.contains("Time"))
            return Custom.getString("Time", " ");
        else
            return null;
    }
    public String getOldImgURL()
    {
        if(Custom.contains("img"))
            return Custom.getString("img", " ");
        else
            return null;
    }
    public void removeFirstPref()
    {
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

            if (Custom.contains(a+"CName" ))
            {
                if (a > 9)// because i want this to happen if there are 10 or more saved sets
                {
                    Custom.edit().remove(0+"CName").apply();
                    Custom.edit().remove(0+"id").apply();
                    Custom.edit().remove(0+"img").apply();
                    Custom.edit().remove(0+"Ingredients").apply();
                    Custom.edit().remove(0+"Directions").apply();
                    Custom.edit().remove(0+"Time").apply();
                    followingName0= Custom.getString(1+"CName", " ");
                    followingName1= Custom.getString(2+"CName", " ");
                    followingName2= Custom.getString(3+"CName", " ");
                    followingName3= Custom.getString(4+"CName", " ");
                    followingName4= Custom.getString(5+"CName", " ");
                    followingName5= Custom.getString(6+"CName", " ");
                    followingName6= Custom.getString(7+"CName", " ");
                    followingName7= Custom.getString(8+"CName", " ");
                    followingName8= Custom.getString(9+"CName", " ");
                    Custom.edit().remove(0+"CName").apply();
                    Custom.edit().remove(1+"CName").apply();
                    Custom.edit().remove(2+"CName").apply();
                    Custom.edit().remove(3+"CName").apply();
                    Custom.edit().remove(4+"CName").apply();
                    Custom.edit().remove(5+"CName").apply();
                    Custom.edit().remove(6+"CName").apply();
                    Custom.edit().remove(7+"CName").apply();
                    Custom.edit().remove(8+"CName").apply();
                    edit = Custom.edit();
                    edit.putString(0+"CName", followingName0);
                    edit.putString(1+"CName", followingName1);
                    edit.putString(2+"CName", followingName2);
                    edit.putString(3+"CName", followingName3);
                    edit.putString(4+"CName", followingName4);
                    edit.putString(5+"CName", followingName5);
                    edit.putString(6+"CName", followingName6);
                    edit.putString(7+"CName", followingName7);
                    edit.putString(8+"CName", followingName8);
                    edit.apply();

                }
            }
        }

    }
}
