package com.example.recipesearch.ui.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;

import com.example.recipesearch.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class settings_activity extends AppCompatActivity
{
    //Both A and B can be true, but with how the search is set up only the Dish search will run
    SwitchCompat Switch_A, Switch_B;
    boolean SwitchState_A, SwitchState_B;
    SharedPreferences Prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Prefs = getSharedPreferences("Pref", 0);
        SwitchState_A = Prefs.getBoolean("Switch_A", true);
        Switch_A = this.<SwitchCompat>findViewById(R.id.Dish_Search);
        Switch_A.setChecked(SwitchState_A);
        SwitchState_B = Prefs.getBoolean("Switch_B", false);
        Switch_B = this.<SwitchCompat>findViewById(R.id.Dish_Search);
        Switch_B.setChecked(SwitchState_B);
        Switch_A.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SwitchState_A = !SwitchState_A;
                Switch_A.setChecked(SwitchState_A);
                SharedPreferences.Editor edit = Prefs.edit();
                edit.putBoolean("Switch_A",SwitchState_A);
                edit.apply();
            }
        });
        Switch_B.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SwitchState_B = !SwitchState_B;
                Switch_B.setChecked(SwitchState_B);
                SharedPreferences.Editor edit = Prefs.edit();
                edit.putBoolean("Switch_B",SwitchState_B);
                edit.apply();
            }
        });
    }
        public boolean GetSwitchA()
    {
        return SwitchState_A;
    }
    public boolean GetSwitchB()
    {
        return SwitchState_B;
    }
}
