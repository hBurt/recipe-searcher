package com.example.recipesearch.ui.meal_planner;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipesearch.R;

import static android.content.Context.MODE_PRIVATE;


public class Meal_Planner_Tomarrow_Fragment extends Fragment
{
    private TextView editTom;
    private String tomarrow;
    public Meal_Planner_Tomarrow_Fragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tomarrow = ((Meal_Planner_Activity)getActivity()).getTomorrow();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal__planner__tomarrow_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        editTom = view.findViewById(R.id.tomarrow);
        editTom.setText(tomarrow);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        tomarrow = editTom.getText().toString();
        ((Meal_Planner_Activity)getActivity()).setTomorrow(tomarrow);
    }
}
