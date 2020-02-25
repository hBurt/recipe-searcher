package com.recipe.recipesearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.recipe.recipesearch.database.Favorite;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class FavoriteRecipeView extends Fragment implements TabLayout.OnTabSelectedListener {

    private Favorite favorite;
    private TextView recipeTitle;
    private ImageView recipeImage;

    private TabLayout tabLayout;

    private ViewPager viewPager;

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

        recipeTitle.setText(favorite.getRecipe().getTitle());

        Picasso.get().load(favorite.getRecipe().getFullURL()).into(recipeImage);

        tabLayout.addTab(tabLayout.newTab().setText("Ingredient"));
        tabLayout.addTab(tabLayout.newTab().setText("Directions"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = root.findViewById(R.id.pager);

        System.out.println("ingredients: " + favorite.getRecipe().getIngredients());
        System.out.println(" directions: " + favorite.getRecipe().getDirections());

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
