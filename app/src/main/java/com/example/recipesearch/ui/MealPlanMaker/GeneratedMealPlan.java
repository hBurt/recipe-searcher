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
    String Meal1Name = "";
    String Meal2Name = "";
    String Meal3Name = "";
    String readyInMin1 = "";
    String readyInMin2 = "";
    String readyInMin3 = "";
    String mealImg1 = "";
    String mealImg2 = "";
    String mealImg3 = "";
    String Cals = "";
    String Protin = "";
    String Fat = "";
    String Carbohydrates = "";
    String ErrorOutput = "Error: Bad Input";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_meal_plan);
        TextView meal1 = findViewById(R.id.Meal1);
        TextView meal2 = findViewById(R.id.Meal2);
        TextView meal3 = findViewById(R.id.Meal3);
        TextView Nutrient = findViewById(R.id.NutrientInfo);
        ImageView img1 = findViewById(R.id.Meal1Img);
        ImageView img2 = findViewById(R.id.Meal2Img);
        ImageView img3 = findViewById(R.id.Meal3Img);
        Cals = MealPlanGeneration.getCals();
        Protin = MealPlanGeneration.getProtin();
        Fat = MealPlanGeneration.getFat();
        Carbohydrates = MealPlanGeneration.getCarbohydrates();
        Meal1Name = MealPlanGeneration.getTitle1();
        Meal2Name = MealPlanGeneration.getTitle2();
        Meal3Name = MealPlanGeneration.getTitle3();
        if (Meal1Name != null)
        {
            readyInMin1 = " Ready in " + MealPlanGeneration.getRim1() + " minutes ";
            readyInMin2 = " Ready in " + MealPlanGeneration.getRim2() + " minutes ";
            readyInMin3 = " Ready in " + MealPlanGeneration.getRim3() + " minutes ";
            mealImg1 = MealPlanGeneration.getImage1();
            mealImg2 = MealPlanGeneration.getImage2();
            mealImg3 = MealPlanGeneration.getImage3();
            Picasso.get().load(mealImg1).into(img1);
            Picasso.get().load(mealImg2).into(img2);
            Picasso.get().load(mealImg3).into(img3);
            String Nutrients = "Calories: " + Cals + "\n" + "Protein: " + Protin + "\n" + "Fat: " + Fat + "\n" + "Carbohydrates: " + Carbohydrates;
            String M1 = Meal1Name + "\n" + readyInMin1;
            String M2 = Meal2Name + "\n" + readyInMin2;
            String M3 = Meal3Name + "\n" + readyInMin3;
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
}
