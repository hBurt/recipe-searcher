package com.recipe.recipesearch.ui.meal_planner;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.recipe.recipesearch.R;
import com.recipe.recipesearch.ui.recipe.RecipeStorage;
import com.google.android.material.tabs.TabLayout;

public class Meal_Planner_Activity extends AppCompatActivity
{
    ViewPager Mview;
    String Notes;
    String Today;
    String Tomorrow;
    SharedPreferences mPrefs;
    SharedPreferences.Editor edit;
    CalendarView Cal = null;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_planner);
        RecipeStorage storage = new RecipeStorage(getApplicationContext());
        Mview = findViewById(R.id.viewPager2);
        mPrefs = getApplicationContext().getSharedPreferences("Saved_Plan", MODE_PRIVATE);
        TabLayout tabM = findViewById(R.id.tabLayout);
        tabM.addTab(tabM.newTab().setText("Today"));
        tabM.addTab(tabM.newTab().setText("Tomorrow"));
        tabM.addTab(tabM.newTab().setText("Notes            "));
       // Cal = findViewById(R.id.Planner_Cal);
        tabM.setTabGravity(TabLayout.GRAVITY_FILL);
        if (mPrefs.contains("Note"))
            Notes = mPrefs.getString("Note", " ");
        if (mPrefs.contains("Day1"))
        {
            String day = mPrefs.getString("Day1"," ");
            String plan = storage.getPlan();
            Today = day + plan;
        }
        else
            Today = storage.getPlan();
        if (mPrefs.contains("Day2"))
            Tomorrow = mPrefs.getString("Day2"," ");
        final MP_TabAdapter adapter = new MP_TabAdapter(this,getSupportFragmentManager(), tabM.getTabCount());
        storage.clearDayPlan();
        Mview.setAdapter(adapter);
        Mview.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabM));
        tabM.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                Mview.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }
    public void setToday(String today)
    {
        Today = today;
    }
    public void setTomorrow(String tomorrow)
    {
        Tomorrow = tomorrow;
    }
    public void setNotes(String note)
    {
        Notes = note;
    }
    public String getToday()
    {
        return Today;
    }
    public String getTomorrow()
    {
        return Tomorrow;
    }
    public String getNotes()
    {
        return Notes;
    }
    public void setTodayNew(String string)
    {
        edit = mPrefs.edit();
        edit.putString("Day1", string);
        edit.apply();
    }
    public void setNotesNew(String string)
    {
        edit = mPrefs.edit();
        edit.putString("Note", string);
        edit.apply();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        edit = mPrefs.edit();
        edit.putString("Note", Notes);
        edit.putString("Day1", Today);
        edit.putString("Day2", Tomorrow);
        edit.apply();

    }
}
