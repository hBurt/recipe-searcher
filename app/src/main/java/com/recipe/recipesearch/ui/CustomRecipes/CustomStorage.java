package com.recipe.recipesearch.ui.CustomRecipes;

import android.content.Context;
import android.content.SharedPreferences;

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
    public void setbool(boolean stringA,String stringB)
    {
        edit = Custom.edit();

        edit.putBoolean(stringB+"Cbool", stringA);

        edit.apply();
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
                edit.putString(stringB+"img", stringA);
        edit.apply();
    }
    public String getCDirections()
    {
        if (Custom.contains(Custom.getString(num+ "CName", "")+"CDirections"))
        {
            String test = Custom.getString(Custom.getString(num + "CName", "") + "CDirections", " ");
            return test;
        }
        else
            return null;
    }
    public String getCIngred()
    {
        if (Custom.contains(Custom.getString(num+ "CName", "")+"CIngredients"))
        {
            String test = Custom.getString(Custom.getString(num + "CName", "") + "CIngredients", " ");
            return test;
        }
        else
            return null;
    }
    public String getCName()
    {
        if(Custom.contains(Custom.getString(num+ "CName", "")+"CName"))
        {
            String test = Custom.getString(Custom.getString(num + "CName", "") + "CName", " ");
            return test;
        }
        else
            return null;
    }
    public String getCTime()
    {
        if(Custom.contains(Custom.getString(num+ "CName", "")+"CTime"))
        {
            String test = Custom.getString(Custom.getString(num + "CName", "") + "CTime", " ");
            return test;
        }
        else
            return null;
    }
    public String getCImgURL()
    {
        if(Custom.contains(Custom.getString(num+ "CName", "")+"img"))
        {
            String test =  Custom.getString(Custom.getString(num + "CName", "") + "img", " ");
            return test;
        }
        else
            return null;
    }
    public boolean getBool()
    {
        boolean test = false;
        if(Custom.contains(Custom.getString(num+ "CName", "")+"Cbool"))
        {
            test = Custom.getBoolean(Custom.getString(num+ "CName", "")+"Cbool", false);
        }
        return test;
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
    public void createRecipe(String name, String directions, String ingredients, String time, String  imgLink, boolean bool)
    {
        removeFirstCRecipe();
        setCTimeAmount(time, name);
        setCRecipeName(name, name);
        setCIngred(ingredients, name);
        setCDirections(directions, name);
        setCImgURL(imgLink, name);
        setbool(bool, name );
        for (int i = 0; i < 10; i++)
        {
            if (Custom.contains(i+"CName" ))
            {}
            else {
                Custom.edit().putString(i + "CName", name).apply();
                break;
            }
        }
    }
    public void setNum(){num++;}
    public void resetNUM(){num = 0;}
    public void removeFirstCRecipe()
    {
        String toBeRemoved = Custom.getString(0+"CName", "");
        String followingName0 = " ";
        String followingName1 = " ";
        String followingName2 = " ";
        String followingName3 = " ";
        String followingName4 = " ";
        String followingName5 = " ";
        String followingName6 = " ";
        String followingName7 = " ";
        String followingName8 = " ";



            if (Custom.contains(toBeRemoved+"CName" ))
            {
                if (getCount() > 9)// because i want this to happen if there are 10 or more saved sets
                {
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


    public boolean atCap()
    {
        return num < getCount();
    }
    public void Clear(){ Custom.edit().clear().apply();}
}
