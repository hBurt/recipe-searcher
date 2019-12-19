package com.example.recipesearch.ui.meal_planner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipesearch.R;
import com.google.android.material.textfield.TextInputEditText;


public class Meal_Planner_Misc_Fragment extends Fragment
{
    static String Note = null;
    public Meal_Planner_Misc_Fragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final EditText text = (EditText) container.findViewById(R.id.Notes);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                Note = text.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return inflater.inflate(R.layout.fragment_meal__planner__misc_, container, false);
    }
    public static String getText()
    {
        return Note;
    }
    public static void setText(String recivedtxt)
    {
        Note = recivedtxt;
    }
}
