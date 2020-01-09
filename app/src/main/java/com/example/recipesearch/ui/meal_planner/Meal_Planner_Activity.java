package com.example.recipesearch.ui.meal_planner;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.recipesearch.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Meal_Planner_Activity extends AppCompatActivity
{
    ViewPager Mview;
    String Notes;
    String Today;
    String Tomorrow;
    SharedPreferences mPrefs;
    SharedPreferences.Editor edit;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        Mview = findViewById(R.id.viewPager);
        mPrefs = getApplicationContext().getSharedPreferences("Saved_Plan", MODE_PRIVATE);
        TabLayout tabM = findViewById(R.id.Tabs);
        tabM.addTab(tabM.newTab().setText("Today"));
        tabM.addTab(tabM.newTab().setText("Tomorrow"));
        tabM.addTab(tabM.newTab().setText("Notes          "));
        tabM.setTabGravity(TabLayout.GRAVITY_FILL);
        if (mPrefs.contains("Note"))
            Notes = mPrefs.getString("Note", " ");
        if (mPrefs.contains("Day1"))
            Today = mPrefs.getString("Day1"," ");
        if (mPrefs.contains("Day2"))
            Tomorrow = mPrefs.getString("Day2"," ");
        final MP_TabAdapter adapter = new MP_TabAdapter(this,getSupportFragmentManager(), tabM.getTabCount());
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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
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
