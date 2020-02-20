package com.recipe.recipesearch.ui.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipe.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.recipe.recipesearch.database.Favorite;
import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.helpers.DatabaseHelper;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class RecipeActivityV2 extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TextView title;
    private TextView time;
    private ImageView image;

    private Recipe recipe;

    private User user;
    private DatabaseHelper databaseHelper;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Button saveRecipe, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_v2);

        recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        Log.v("RecipeActivityV2", recipe.display());

        btnHome = findViewById(R.id.button_home_v2);
        saveRecipe = findViewById(R.id.button_save_recipe_v2);

        title = findViewById(R.id.recipe_title_v2);
        image = findViewById(R.id.recipe_image_v2);
        time = findViewById(R.id.recipe_time_v2);

        tabLayout = findViewById(R.id.recipe_tablayout_v2);
        viewPager = findViewById(R.id.pager_v2);

        title.setText(recipe.getTitle());
        String timeInMin = recipe.getReadyInMiniutes() + " minutes";
        time.setText(timeInMin);
        Picasso.get().load(recipe.getFullURL()).into(image);

        tabLayout.addTab(tabLayout.newTab().setText("Ingredient"));
        tabLayout.addTab(tabLayout.newTab().setText("Directions"));
        tabLayout.addTab(tabLayout.newTab().setText("Next Recipe"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabHandler2 adapter = new TabHandler2(getSupportFragmentManager(), tabLayout.getTabCount(), recipe);

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

        saveRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecipe();
                System.out.println("Saving recipe");
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(RecipeActivityV2.this, MainActivity.class);
                startActivity(in);
            }
        });

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

    private void saveRecipe(){

        user = (User) getIntent().getSerializableExtra("databaseUserr");

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.rebuildDatabase();

        //recipe.setDirections(Recipe_Directions_Tab_Fragment.getDirections());
        //recipe.setIngredients(Recipe_Ingredient_Tab_Fragment.getIngredients());

        Favorite favoite = new Favorite(0, recipe);

        user.getFavorites().add(favoite);

        Log.d("RecipeActivityV2", "TO save: " + favoite.getRecipe().display());

        //databaseHelper.getDatabase().getUserDao().updateDetails(user);
        databaseHelper.updateCurrentUser(false, favoite);
    }
}
