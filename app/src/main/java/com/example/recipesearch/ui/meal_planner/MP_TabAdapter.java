package com.example.recipesearch.ui.meal_planner;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.recipesearch.ui.meal_planner.Meal_Planner_Misc_Fragment;
import com.example.recipesearch.ui.meal_planner.Meal_Planner_T_Fragment;
import com.example.recipesearch.ui.meal_planner.Meal_Planner_Tomarrow_Fragment;

import java.util.ArrayList;
import java.util.List;

class MP_TabAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public MP_TabAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Meal_Planner_T_Fragment Today = new Meal_Planner_T_Fragment();
                return Today;
            case 1:
                Meal_Planner_Tomarrow_Fragment Tomarrow = new Meal_Planner_Tomarrow_Fragment();
                return Tomarrow;
            case 2:
                Meal_Planner_Misc_Fragment Misc = new Meal_Planner_Misc_Fragment();
                return Misc;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}