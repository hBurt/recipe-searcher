package com.example.recipesearch.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.APIComunication.MealPlanGeneration;

public class MealPlanActivity extends AppCompatActivity
{
    static String timePeriod = "";
    static String dietaryPrefrence = "";
    static String caloricNum = "";
    static String exclusions= "";

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
        begin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MealPlanGeneration gen = new MealPlanGeneration();
               // gen.execute(); // will generate the meal plan
            }
        });
    }
    public static String getTimePeriod(){return timePeriod;}
    public static String getDietaryPrefrence() {return dietaryPrefrence;}
    public static String getCaloricNum() {return caloricNum;}
    public static String getExclusions() {return exclusions;}
}
