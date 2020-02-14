package com.recipe.recipesearch.ui.meal_planner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipesearch.R;


public class Meal_Planner_Misc_Fragment extends Fragment
{
    private TextView editNotes;
    private String note;
    public Meal_Planner_Misc_Fragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
         note = ((Meal_Planner_Activity)getActivity()).getNotes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal__planner__misc_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        editNotes = view.findViewById(R.id.Notes);
        editNotes.setText(note);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        note = editNotes.getText().toString();
        ((Meal_Planner_Activity)getActivity()).setNotes(note);

    }
}
