package com.example.recipesearch.ui.meal_planner;
import android.os.Build;
import android.os.Bundle;
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
    Meal_Planner_T_Fragment MPTR = new Meal_Planner_T_Fragment(); // case 1
    Meal_Planner_Tomarrow_Fragment MPTOF = new Meal_Planner_Tomarrow_Fragment(); // case 0
    Meal_Planner_Misc_Fragment MPMF = new Meal_Planner_Misc_Fragment(); //case 2
    private TextView tex; // all this shit is declared here because it just made it easy incase it was needed outside onCreate
    private ImageView pic;
    private TabLayout tab;
    ViewPager view;
    private String RecipeName = "Test Text";
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        //Toolbar tool = findViewById(R.id.Recpie_Tool_Bar);
        view = findViewById(R.id.viewPager);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        tab.addTab(tab.newTab().setText("Today"));
        tab.addTab(tab.newTab().setText("Tomarrow"));
        tab.addTab(tab.newTab().setText("Notes"));
        tex.setText(RecipeName);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        final MP_TabAdapter adapter = new MP_TabAdapter(this,getSupportFragmentManager(), tab.getTabCount());
        view.setAdapter(adapter);
        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                view.setCurrentItem(tab.getPosition());
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
