package com.recipe.recipesearch.ui.MealPlanMaker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.recipe.recipesearch.R;
import com.recipe.recipesearch.ui.APIComunication.MealPlanGeneration;

import com.recipe.recipesearch.R;
import com.recipe.recipesearch.ui.APIComunication.MealPlanGenV2;

public class MealPlanActivity extends AppCompatActivity
{
    static String timePeriod = "";
    static String dietaryPrefrence = "";
    static String caloricNum = "";
    static String exclusions= "";
    private static Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        EditText TOD = findViewById(R.id.TimeOfDay);
        EditText Cals = findViewById(R.id.CalTarget);
        EditText DietPref = findViewById(R.id.DietPrefrence); // might be used
        EditText exception = findViewById(R.id.foodExceptions);
        Button begin = findViewById(R.id.submit);
        final LoadingScreenMP generating = new LoadingScreenMP();
        TOD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s)
            {
                timePeriod = s.toString();
            }
        });
        Cals.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s)
            {
                caloricNum = s.toString();
            }
        });
        DietPref.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s)
            {
                dietaryPrefrence = s.toString();
            }
        });
        exception.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s)
            {
                exclusions = s.toString();
            }
        });
        h = new  Handler(){
            @Override
            public void handleMessage(Message msg)
            {

                Intent in = new Intent(MealPlanActivity.this, GeneratedMealPlan.class);
                startActivity(in);
                generating.destroy();
            }
        };

        begin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GeneratedMealPlan.doReset();
                Intent loading = new Intent(MealPlanActivity.this, LoadingScreenMP.class);
                startActivity(loading);
                MealPlanGenV2 gen = new MealPlanGenV2();
                gen.execute(); // will generate the meal plan
                h.sendEmptyMessageDelayed(0, 3000);
            }
        });
    }
    public static String getTimePeriod()
    {
        if (timePeriod.length() > 1)
        return timePeriod;
        else
            return null;
    }
    public static String getDietaryPrefrence()
    {
        if (dietaryPrefrence.length() > 2)
        return dietaryPrefrence;
        else
            return null;
    }
    public static String getCaloricNum()
    {
        if(caloricNum.length() > 1)
        return caloricNum;
        else
            return null;
    }
    public static String getExclusions()
    {
        if (exclusions.length() > 2)
        return exclusions;
        else
            return null;
    }
}
