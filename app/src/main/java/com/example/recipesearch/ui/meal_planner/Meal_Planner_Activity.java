package com.example.recipesearch.ui.meal_planner;
import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
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
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        //Toolbar tool = findViewById(R.id.Recpie_Tool_Bar);
        Mview = findViewById(R.id.viewPager);
        tabM = findViewById(R.id.Tabs);
        Cal = findViewById(R.id.calender);
        tabM.addTab(tabM.newTab().setText("Today"));
        tabM.addTab(tabM.newTab().setText("Tomarrow"));
        tabM.addTab(tabM.newTab().setText("Notes"));
        tabM.setTabGravity(TabLayout.GRAVITY_FILL);
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
