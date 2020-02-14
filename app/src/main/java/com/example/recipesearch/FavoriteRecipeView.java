package com.example.recipesearch;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.recipesearch.database.Favorite;
import com.example.recipesearch.ui.CustomDialog;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class FavoriteRecipeView extends Fragment implements TabLayout.OnTabSelectedListener {

    private Favorite favorite;
    private TextView recipeTitle;
    private ImageView recipeImage;

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private Button buttonSetRating;

    public FavoriteRecipeView(){
        // Required empty public constructor
    }

    public FavoriteRecipeView(Favorite favorite) {
        this.favorite = favorite;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite_recipe_view, container, false);

        recipeTitle = root.findViewById(R.id.favorite_recipe_title);
        recipeImage = root.findViewById(R.id.favorite_recipe_image);
        tabLayout = root.findViewById(R.id.favorite_recipe_tablayout);
        buttonSetRating = root.findViewById(R.id.favorite_button_set_rating);

        recipeTitle.setText(favorite.getRecipe().getTitle());

        Picasso.get().load(favorite.getRecipe().getFullURL()).into(recipeImage);

        tabLayout.addTab(tabLayout.newTab().setText("Ingredient"));
        tabLayout.addTab(tabLayout.newTab().setText("Directions"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = root.findViewById(R.id.pager);

        TabHandler adapter = new TabHandler(getActivity().getSupportFragmentManager(), tabLayout.getTabCount(), favorite.getRecipe());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                //refresh();
            }
        });

        buttonSetRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog cdd=new CustomDialog(getActivity(), R.style.Theme_Dialog, favorite);
                cdd.show();
            }
        });

        return root;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
