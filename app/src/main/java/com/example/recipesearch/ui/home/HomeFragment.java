package com.example.recipesearch.ui.home;

<<<<<<< HEAD
import android.graphics.PorterDuff;
=======
import android.os.Build;
>>>>>>> 284598d4f77bb08a9665252e7b14a37b468fce5f
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.ImageView;
=======
import android.widget.SearchView;
>>>>>>> 284598d4f77bb08a9665252e7b14a37b468fce5f
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.core.content.ContextCompat;
=======
import androidx.annotation.RequiresApi;
>>>>>>> 284598d4f77bb08a9665252e7b14a37b468fce5f
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.recipe.RecipeFragment;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Scanner;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = root.findViewById(R.id.text_search);
        final SearchView search = root.findViewById(R.id.search_box);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //Do img color overlay
        ImageView iv = getActivity().findViewById(R.id.imageView);
        iv.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

        EditText et = getActivity().findViewById(R.id.search_bar_edit_text);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // openFragment();
                System.out.println("Changed ################################");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }

}