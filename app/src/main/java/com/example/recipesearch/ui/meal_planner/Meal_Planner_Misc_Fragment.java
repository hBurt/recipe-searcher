package com.example.recipesearch.ui.meal_planner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipesearch.R;


public class Meal_Planner_Misc_Fragment extends Fragment
{
    public Meal_Planner_Misc_Fragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal__planner__misc_, container, false);
    }
}
