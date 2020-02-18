package com.example.recipesearch.ui.CustomRecipes;

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
    public int getNum()
    {
        return num;
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
        for (int i = 0; i < 25; i++)
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
        String Names[] = new String[25];


            if (Custom.contains(toBeRemoved+"CName" ))
            {
                if (getCount() > 24)// because i want this to happen if there are 10 or more saved sets
                {
                    Custom.edit().remove(toBeRemoved+"CName").apply();
                    Custom.edit().remove(toBeRemoved+"id").apply();
                    Custom.edit().remove(toBeRemoved+"img").apply();
                    Custom.edit().remove(toBeRemoved+"Ingredients").apply();
                    Custom.edit().remove(toBeRemoved+"Directions").apply();
                    Custom.edit().remove(toBeRemoved+"Time").apply();
                    for (int i = 0; i < 25; i++)
                    {
                        if (Custom.getString(i+"CName", " ") != null)
                        Names[i] = Custom.getString(i+"CName", " ");
                    }
                    for (int i = 0; i < 25; i++)
                    {
                        Custom.edit().remove(i+"CName").apply();
                    }
                    edit = Custom.edit();
                    for (int i = 0; i < 25; i++)
                    {
                        if (Names[i] != null)
                        edit.putString(i+"CName", Names[i]);
                    }
                    edit.apply();

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
