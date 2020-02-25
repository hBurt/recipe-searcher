package com.recipe.recipesearch.ui.meal_planner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recipe.recipesearch.R;


public class Meal_Planner_T_Fragment extends Fragment {

    private TextView editToday;
    private static String today;

    public Meal_Planner_T_Fragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        today = ((Meal_Planner_Activity)getActivity()).getToday();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal__planner__t_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        editToday = view.findViewById(R.id.Today);
        editToday.setText(today);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        today = editToday.getText().toString();
        ((Meal_Planner_Activity)getActivity()).setToday(today);
    }
}
