package com.recipe.recipesearch.ui.Settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.recipe.recipesearch.R;

import androidx.appcompat.app.AppCompatActivity;

public class settings_activity extends AppCompatActivity
{
    // this will need some updates
    Switch Switch_Dish, Switch_Ingredient;
    static boolean SwitchState_Dish, SwitchState_Ingredient;
    SharedPreferences Prefs;
    SharedPreferences.Editor edit;
    TextView hint1, hint2, discription;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        if (Prefs.contains("Switch_A"))
            SwitchState_Dish = Prefs.getBoolean("Switch_A", true);
        else
            SwitchState_Dish = true;
        Switch_Dish = this.findViewById(R.id.Dish_Search);
        if (Prefs.contains("Switch_B"))
            SwitchState_Ingredient = Prefs.getBoolean("Switch_B", false);
        else
            SwitchState_Ingredient = false;
        Switch_Ingredient = this.findViewById(R.id.Ingredient_Search);
        hint1 = findViewById(R.id.Hint1);
        hint2 = findViewById(R.id.Hint2);
        hint1.setText("Enter the name of what you want to cook, can be a general name or the full name");
        hint2.setText("Enter the name(s) of ingredients");
        Switch_Dish.setChecked(SwitchState_Dish);
        Switch_Ingredient.setChecked(SwitchState_Ingredient);
        discription = findViewById(R.id.Discription);
        discription.setText("Changes the method of searching for a recipe, search by Dish is the default, and will be on if both are either turned off or on.");
        Switch_Dish.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SwitchState_Dish = SwitchState_Dish != true;

            }
        });
        Switch_Ingredient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SwitchState_Ingredient = SwitchState_Ingredient == false;
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        edit = Prefs.edit();
        if (SwitchState_Dish == SwitchState_Ingredient)
        {
            SwitchState_Dish = true;
            SwitchState_Ingredient = false;
        }
        edit.putBoolean("Switch_A", SwitchState_Dish);
        edit.putBoolean("Switch_B", SwitchState_Ingredient);
        edit.apply();
    }

    public static boolean GetSwitchA()
    {
        return SwitchState_Dish;
    }
      public static boolean GetSwitchB()
    {
        return SwitchState_Ingredient;
    }
    public static void setSwitchA(boolean bool){ SwitchState_Dish = bool;}
}
