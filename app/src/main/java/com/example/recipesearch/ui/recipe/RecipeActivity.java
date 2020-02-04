package com.example.recipesearch.ui.recipe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.example.recipesearch.database.Recipe;
import com.example.recipesearch.database.User;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class RecipeActivity extends AppCompatActivity
{
    private TextView tex;
    private ImageView pic;
    private TabLayout tab;
    private TextView TTM;
    private static Handler h;
    Drawable image = null;
    ViewPager view;
    static String ID = null;
    static String RecipeName = "Beef Salpicao"; // example for test purposes
    static String imgName = " ";
    static String timeToMake = "XX";
    private static String baseURI = null;
    String wantedImg;
    private static String directions;
    private static String ingredients;
    public static int i = 1;


    Button saveRecipe, btnHome;

    DatabaseHelper databaseHelper;
    User user;

    public static boolean ReadTheDamBook = false;

    public static String getBaseURI() {
        return baseURI;
    }

    public static void setBaseURI(String baseURI) {
        RecipeActivity.baseURI = baseURI;
    }

    public static void setDirections(String directions) {
        RecipeActivity.directions = directions;
    }

    public static void setIngredients(String ingredients) {
        RecipeActivity.ingredients = ingredients;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);

        saveRecipe = findViewById(R.id.btn_save_recipe);
        btnHome = findViewById(R.id.button_home);


        if (ReadTheDamBook == true)
        {
            useOld();
            ReadTheDamBook = false;
        }
        view = findViewById(R.id.viewPager);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        TTM = findViewById(R.id.Time);
        if (timeToMake != "Unavailable")
        TTM.setText(timeToMake + " minutes");
        wantedImg = imgName;
        if (wantedImg.length() > 3)
        {
            String nWantedImg = getBaseURI() + wantedImg;
            Picasso.get().load(nWantedImg).into(pic);
        }
        tab.addTab(tab.newTab().setText("Ingredients"));
        tab.addTab(tab.newTab().setText("Directions"));
        tab.addTab(tab.newTab().setText("Next Recipe"));
        tex.setText(RecipeName);
        RecipeStorage storage = new RecipeStorage(getApplicationContext());
        storage.setOGName(RecipeName);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        final MyTabAdapter adapter = new MyTabAdapter(this,getSupportFragmentManager(), tab.getTabCount());
        view.setAdapter(adapter);
        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                view.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                refresh();
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
                Intent in = new Intent(RecipeActivity.this, MainActivity.class);
                startActivity(in);
            }
        });

    }
    public static void setRecipeName(String someName)
    {
        RecipeName = someName;
    }
    public static String getRecipeName()
    {
        return RecipeName;
    }
    public void refresh()
    {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
    public void refreshAndSave()
    {
        if (ID != null)
        {
            RecipeStorage storage = new RecipeStorage(getApplicationContext());
            storage.setDirections();
            storage.setIngred();
            storage.setImgURL(imgName);
            storage.setRecipeID(ID);
            storage.setRecipeName(RecipeName);
            storage.setTimeAmount(timeToMake);
        }
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
    public static void setPic( String img)
    {
        imgName = img;
    }
    public static void setTime(String time)
    {
        timeToMake = time;
    }
    public static void setID(String id)
    {
        ID = id;
    }
    public void useOld()
    {
        RecipeStorage storage = new RecipeStorage(getApplicationContext());
        imgName = storage.getOldImgURL();
        RecipeName = storage.getOldName();
        Recipe_Ingredient_Tab_Fragment.setIngredients(storage.getOldIngred());
        Recipe_Directions_Tab_Fragment.setDirections(storage.getOldDirections());
        timeToMake = storage.getOldTime();
        ID = storage.getOldID();

    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // should save as long as i have the id
        if (ID != null)
        {
            // should prevent rand and next from overwriting the original
            RecipeStorage storage = new RecipeStorage(getApplicationContext());
            if (storage.getOGName().equals(RecipeName))
            {
                storage.removePref();
                storage.removeFirstPref();
                storage.setDirections();
                storage.setIngred();
                storage.setImgURL(imgName);
                storage.setRecipeID(ID);
                storage.setRecipeName(RecipeName);
                storage.setTimeAmount(timeToMake);
            }
        }
    }

    private void saveRecipe(){

        user = (User) getIntent().getSerializableExtra("databaseUserr");

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.rebuildDatabase();

        int id = Integer.parseInt(ID);
        int time = Integer.parseInt(timeToMake);

        Recipe recipe = new Recipe(id, RecipeName, time, getBaseURI() + imgName);
        recipe.setDirections(directions);
        recipe.setIngredients(ingredients);
        Favorite favoite = new Favorite(0 ,recipe);

        user.getFavorites().add(favoite);
        databaseHelper.getDatabase().getUserDao().updateDetails(user);
    }
    public static void setReadTheBook(boolean bool)
    {
        ReadTheDamBook = bool;
    }
}
