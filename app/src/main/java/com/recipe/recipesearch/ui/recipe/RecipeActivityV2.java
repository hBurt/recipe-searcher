package com.recipe.recipesearch.ui.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.recipe.recipesearch.MainActivity;
import com.recipe.recipesearch.R;
import com.recipe.recipesearch.database.Favorite;
import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.helpers.DatabaseHelper;
import com.recipe.recipesearch.ui.CustomRecipes.CustomStorage;
import com.recipe.recipesearch.ui.search_result.SearchActivity;
import com.recipe.recipesearch.ui.search_result.SearchingActivity;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class RecipeActivityV2 extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TextView title;
    private TextView time;
    private ImageView image;
    private static boolean isOpen = false;
    private static boolean useSimilar = false;
    private Recipe recipe;

    private User user;
    private DatabaseHelper databaseHelper;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Button saveRecipe, btnHome;
    Intent intent ;
    Bundle bundle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_v2);
        isOpen = true;
        if (!useSimilar)
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        else {
            recipe = SearchActivity.getSimRecipe();
            useSimilar = false;
        }
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
        String test = recipe.getFullURL();
        Picasso.get().load(recipe.getFullURL()).into(image);
        tabLayout.addTab(tabLayout.newTab().setText("Ingredient"));
        tabLayout.addTab(tabLayout.newTab().setText("Directions"));
        tabLayout.addTab(tabLayout.newTab().setText("Next Recipe"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabHandler2 adapter = new TabHandler2(getSupportFragmentManager(), tabLayout.getTabCount(), recipe, this);

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
                isOpen = false;
                Intent in = new Intent(RecipeActivityV2.this, MainActivity.class);
                finish();
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
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        isOpen = false;
        CustomStorage cs = new CustomStorage(getApplicationContext());
        cs.resetNUM();
        SearchingActivity.SA.finish();
    }

    private void saveRecipe(){

        user = (User) getIntent().getSerializableExtra("databaseUserr");

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.rebuildDatabase();

        Favorite favoite = new Favorite(0, recipe);

        user.getFavorites().add(favoite);
        databaseHelper.getDatabase().getUserDao().updateDetails(user);
    }
    public static boolean getIsOpen(){return isOpen;}

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        isOpen = false;
        if(SearchingActivity.getIsOpen())
        SearchingActivity.SA.finish();
    }
    public void refresh()
    {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
    public void END()
    {
        finish();
    }
    public void setRecipe(Recipe rec){ recipe = rec; }
    public static void setUseSimilar(){ useSimilar = true;}
}
