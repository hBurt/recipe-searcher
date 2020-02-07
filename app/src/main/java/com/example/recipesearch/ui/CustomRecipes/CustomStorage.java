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
    public void setCDirections (String stringA,String stringB)
    {
        edit = Custom.edit();

                edit.putString(stringB+"CDirections", stringA);

        edit.apply();
    }
    public void setCIngred(String stringA,String stringB)
    {
        edit = Custom.edit();
                edit.putString(stringB+"CIngredients",stringA);
        edit.apply();
    }
    public void setCRecipeName(String stringA,String stringB)
    {
        edit = Custom.edit();
                edit.putString(stringB + "CName", stringA);
        edit.apply();
    }
    public void setCTimeAmount(String stringA,String stringB)
    {
        edit = Custom.edit();
                edit.putString(stringB+"CTime", stringA);
        edit.apply();
    }
    public void setCImgURL(String stringA,String stringB )
    {
        edit = Custom.edit();
                edit.putString(stringB+" img", stringA);
        edit.apply();
    }
    public String getCDirections()
    {
        if (Custom.contains(num+"CDirections"))
            return Custom.getString(num+"CDirections", " ");
        else
            return null;
    }
    public String getCIngred()
    {
        if (Custom.contains(num+"CIngredients"))
            return Custom.getString(num+"CIngredients", " ");
        else
            return null;
    }
    public String getCName()
    {
        if(Custom.contains(num+"CName"))
            return Custom.getString(num+"CName", " ");
        else
            return null;
    }
    public String getCTime()
    {
        if(Custom.contains(num+"CTime"))
            return Custom.getString(num+"CTime", " ");
        else
            return null;
    }
    public String getCImgURL()
    {
        if(Custom.contains(num+"img"))
            return Custom.getString(num+"img", " ");
        else
            return null;
    }
    public int getCount()
    {
        int a = 0;
        // just room for error, should never return more then 10
       for (int i = 0; i < 15; i++)
       {
           if (Custom.contains(i+"CName" ))
               a++;
       }
        return a;
    }
    public void createRecipe(String name, String directions, String ingredients, String time, String  imgLink)
    {
        if (getCount() > 9){removeFirstCRecipe();}
        setCTimeAmount(time, name);
        setCRecipeName(name, name);
        setCIngred(ingredients, name);
        setCDirections(directions, name);
        setCImgURL(imgLink, name);
        for (int i = 0; i < 10; i++)
        {
            if (Custom.contains(i+"CName" ))
            {}
            else {Custom.edit().putString(i + " CName", name);}
        }
    }
    public void setNum(){num++;}
    public void resetNUM(){num = 0;}
    public void removeFirstCRecipe()
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
                    String toBeRemoved = Custom.getString(0+"CName", "");
                    Custom.edit().remove(toBeRemoved+"CName").apply();
                    Custom.edit().remove(toBeRemoved+"id").apply();
                    Custom.edit().remove(toBeRemoved+"img").apply();
                    Custom.edit().remove(toBeRemoved+"Ingredients").apply();
                    Custom.edit().remove(toBeRemoved+"Directions").apply();
                    Custom.edit().remove(toBeRemoved+"Time").apply();
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
    public boolean atCap()
    {
        if (num < getCount())
            return true;
        else
            return false;
    }
    public void Clear(){ Custom.edit().clear().apply();}
}
