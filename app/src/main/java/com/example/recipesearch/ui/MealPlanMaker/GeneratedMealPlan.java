package com.example.recipesearch.ui.MealPlanMaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.APIComunication.MealPlanGeneration;
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
    String ErrorOutput = "Error: Bad Input";
    private static boolean reset = false;
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
        TextView meal1 = findViewById(R.id.Meal1);
        TextView meal2 = findViewById(R.id.Meal2);
        TextView meal3 = findViewById(R.id.Meal3);
        TextView Nutrient = findViewById(R.id.NutrientInfo);
        ImageView img1 = findViewById(R.id.Meal1Img);
        ImageView img2 = findViewById(R.id.Meal2Img);
        ImageView img3 = findViewById(R.id.Meal3Img);
        if (Meal1Name != null)
        {
           String readyInMin1v2 = " Ready in " + readyInMin1 + " minutes ";
           String readyInMin2v2 = " Ready in " + readyInMin2 + " minutes ";
           String readyInMin3v2 = " Ready in " + readyInMin3 + " minutes ";
            Picasso.get().load(mealImg1).into(img1);
            Picasso.get().load(mealImg2).into(img2);
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
}
