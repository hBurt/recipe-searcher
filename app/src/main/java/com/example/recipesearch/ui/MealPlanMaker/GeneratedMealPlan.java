package com.example.recipesearch.ui.MealPlanMaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipesearch.R;
import com.example.recipesearch.database.User;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.example.recipesearch.ui.APIComunication.MealPlanGeneration;
import com.example.recipesearch.ui.APIComunication.SelectedMealData;
import com.example.recipesearch.ui.meal_planner.Meal_Planner_Activity;
import com.example.recipesearch.ui.meal_planner.Meal_Planner_T_Fragment;
import com.example.recipesearch.ui.recipe.RecipeActivity;
import com.example.recipesearch.ui.recipe.RecipeStorage;
import com.example.recipesearch.ui.search_result.SearchActivity;
import com.example.recipesearch.ui.search_result.SearchingActivity;
import com.squareup.picasso.Picasso;

public class GeneratedMealPlan extends AppCompatActivity
{
    private static String Meal1Name = "";
    private static String Meal2Name = "";
    private static String Meal3Name = "";
    private static String readyInMin1 = "";
    private static String readyInMin2 = "";
    private static String readyInMin3 = "";
    private static String mealImg1 = "";
    private static String mealImg2 = "";
    private static String mealImg3 = "";
    private static String Cals = "";
    private static String Protin = "";
    private static String Fat = "";
    private static String Carbohydrates = "";
    private static String id1 = "";
    private static String id2 = "";
    private static String id3 = "";
    String ErrorOutput = "Error: Bad Input";
    private static boolean reset = false;
    private static Handler h;
    User user;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_meal_plan);
        if (reset == true)
        {
            reset = false;
            refresh();
        }
        user = (User) getIntent().getSerializableExtra("databaseUser");
        TextView meal1 = findViewById(R.id.Meal1);
        TextView meal2 = findViewById(R.id.Meal2);
        TextView meal3 = findViewById(R.id.Meal3);
        TextView Nutrient = findViewById(R.id.NutrientInfo);
        ImageView img1 = findViewById(R.id.Meal1Img);
        ImageView img2 = findViewById(R.id.Meal2Img);
        ImageView img3 = findViewById(R.id.Meal3Img);
        if (Meal1Name != null)
        {
           String readyInMin1v2 = "Ready in " + readyInMin1 + " minutes ";
           String readyInMin2v2 = "Ready in " + readyInMin2 + " minutes ";
           String readyInMin3v2 = "Ready in " + readyInMin3 + " minutes ";
            if (!mealImg1.contains("https://spoonacular.com/recipeImages/"))
            {
                String nWantedImg = "https://spoonacular.com/recipeImages/" + mealImg1;
                Picasso.get().load(nWantedImg).into(img1);
            }
            else
                Picasso.get().load(mealImg1).into(img1);
            if (!mealImg2.contains("https://spoonacular.com/recipeImages/"))
            {
                String nWantedImg = "https://spoonacular.com/recipeImages/" + mealImg2;
                Picasso.get().load(nWantedImg).into(img2);
            }
            else
                Picasso.get().load(mealImg2).into(img2);
            if (!mealImg3.contains("https://spoonacular.com/recipeImages/"))
            {
                String nWantedImg = "https://spoonacular.com/recipeImages/" + mealImg3;
                Picasso.get().load(nWantedImg).into(img3);
            }
            else
                Picasso.get().load(mealImg3).into(img3);
            String Nutrients = "Calories: " + Cals + "\n" + "Protein: " + Protin + "\n" + "Fat: " + Fat + "\n" + "Carbohydrates: " + Carbohydrates;
            String M1 = Meal1Name + "\n" + readyInMin1v2;
            String M2 = Meal2Name + "\n" + readyInMin2v2;
            String M3 = Meal3Name + "\n" + readyInMin3v2;
            meal1.setText(M1);
            meal2.setText(M2);
            meal3.setText(M3);
            Nutrient.setText(Nutrients);
        }
        else {
            meal1.setText(ErrorOutput);
            meal2.setText(ErrorOutput);
            meal3.setText(ErrorOutput);
            Nutrient.setText(ErrorOutput);
        }
        h = new  Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                Intent in = new Intent(GeneratedMealPlan.this, RecipeActivity.class);
                in.putExtra("databaseUserr", user);
                startActivity(in);
            }
        };
        meal1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(GeneratedMealPlan.this, SearchingActivity.class);
                startActivity(in);
                RecipeActivity.setID(id1);
                RecipeActivity.setTime(readyInMin1);
                RecipeActivity.setRecipeName(Meal1Name);
                RecipeActivity.setPic(mealImg1);
                SelectedMealData data = new SelectedMealData();
                data.setId(id1);
                data.execute();
                h.sendEmptyMessageDelayed(0, 3000);// a delay to allow the search to finish before the recipe page pops up
            }
        });
        meal2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(GeneratedMealPlan.this, SearchingActivity.class);
                startActivity(in);
                RecipeActivity.setID(id2);
                RecipeActivity.setTime(readyInMin2);
                RecipeActivity.setRecipeName(Meal2Name);
                RecipeActivity.setPic(mealImg2);
                SelectedMealData data = new SelectedMealData();
                data.setId(id2);
                data.execute();
                h.sendEmptyMessageDelayed(0, 3000);// a delay to allow the search to finish before the recipe page pops up
            }
        });
        meal3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(GeneratedMealPlan.this, SearchingActivity.class);
                startActivity(in);
                RecipeActivity.setID(id3);
                RecipeActivity.setTime(readyInMin3);
                RecipeActivity.setRecipeName(Meal3Name);
                RecipeActivity.setPic(mealImg3);
                SelectedMealData data = new SelectedMealData();
                data.setId(id3);
                data.execute();
                h.sendEmptyMessageDelayed(0, 3000);// a delay to allow the search to finish before the recipe page pops up
            }
        });
        RecipeStorage storage = new RecipeStorage(getApplicationContext());
        storage.setDayPlan(Meal1Name + "\n"+Meal2Name+ "\n"+Meal3Name );
    }
    public void refresh()
    {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
    public static void doReset ()
    {
        reset = true;
    }
    public static void setMeal1Name(String string){ Meal1Name = string;}
    public static void setMeal2Name(String string){ Meal2Name = string;}
    public static void setMeal3Name(String string){ Meal3Name = string;}
    public static void setReadyInMin1(String s){readyInMin1 = s;}
    public static void setReadyInMin2(String s){readyInMin2 = s;}
    public static void setReadyInMin3(String s){readyInMin3 = s;}
    public static void setMealImg1 (String s){mealImg1 = s;}
    public static void setMealImg2 (String s){mealImg2 = s;}
    public static void setMealImg3 (String s){mealImg3 = s;}
    public static void setCals (String s){Cals = s;}
    public static void setProtin (String s){Protin = s;}
    public static void setFat (String s ) {Fat = s;}
    public static void setCarbohydrates(String s) {Carbohydrates = s;}
    public static void setID1(String s){ id1 = s;}
    public static void setID2(String s){ id2 = s;}
    public static void setID3(String s){ id3 = s;}

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        LoadingScreenMP.LSMP.finish();
    }
}
