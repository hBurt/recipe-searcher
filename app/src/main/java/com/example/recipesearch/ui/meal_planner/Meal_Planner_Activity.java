package com.example.recipesearch.ui.meal_planner;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.recipesearch.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Meal_Planner_Activity extends AppCompatActivity
{
    private TabLayout tabM;
    ViewPager Mview;
    CalendarView Cal;
    String Notes;
    String Today;
    String Tomorrow;
    static EditText editNotes;
    static EditText  editToday;
    static EditText editTomorrow;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SharedPreferences myPrefs = this.getSharedPreferences("prefID", Context.MODE_PRIVATE);
        editor =  myPrefs.edit();
        Notes = myPrefs.getString("NoteKey","Default");
        Today = myPrefs.getString("TodayKey", "Default");
        Tomorrow = myPrefs.getString("TomorrowKey","Default");
        setContentView(R.layout.recipe_page);
        Mview = findViewById(R.id.viewPager);
        tabM = findViewById(R.id.Tabs);
        Cal = findViewById(R.id.calender);
        tabM.addTab(tabM.newTab().setText("Today"));
        tabM.addTab(tabM.newTab().setText("Tomarrow"));
        tabM.addTab(tabM.newTab().setText("Notes"));
        tabM.setTabGravity(TabLayout.GRAVITY_FILL);
        editNotes = (EditText) findViewById(R.id.Notes);
        editToday = (EditText) findViewById(R.id.Today);
        editTomorrow = (EditText) findViewById(R.id.tomarrow);
        final MP_TabAdapter adapter = new MP_TabAdapter(this,getSupportFragmentManager(), tabM.getTabCount());
        Mview.setAdapter(adapter);
        Mview.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabM));
        tabM.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                Mview.setCurrentItem(tab.getPosition());
                if (Notes.length() > 1)
                {
                    editor.putString("NoteKey", Notes);
                    editor.apply();
                    editor.commit();
                }
               if (Today.length() > 1)
               {
                   editor.putString("TodayKey", Today);
                   editor.apply();
                   editor.commit();
               }
                if (Tomorrow.length() > 1)
                {
                    editor.putString("TomorrowKey", Tomorrow);
                    editor.apply();
                    editor.commit();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
            }
        });
    }
}
