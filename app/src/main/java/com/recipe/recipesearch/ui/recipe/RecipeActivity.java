package com.recipe.recipesearch.ui.recipe;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.recipe.recipesearch.R;
import com.recipe.recipesearch.database.Favorite;
import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.helpers.DatabaseHelper;
import com.recipe.recipesearch.ui.CustomRecipes.CustomStorage;
import com.recipe.recipesearch.ui.search_result.SearchingActivity;
import com.google.android.material.tabs.TabLayout;

public class RecipeActivity extends AppCompatActivity
{
    private TextView tex;
    private ImageView pic;
    private TabLayout tab;
    private TextView TTM;
    private static Uri myUri = null;
    private static Handler h;
    ViewPager view;
    static String RecipeName = "Beef Salpicao"; // example for test purposes
    static String imgName = " ";
    static String timeToMake = "XX";
    private static String baseURI = null;
    private static String directions;
    private static String ingredients;
    private static String takenPic = null;
    String wantedImg;
    String recipeTitle = null;
    public static int i = 1;
    static boolean doIReset = false;
    private static boolean usePic = false;

    Button saveRecipe, btnHome;

    DatabaseHelper databaseHelper;
    User user;
    public static boolean ReadTheDamBook = false;

    Recipe recipe;

    public static void setPicUri(String cImgURL)
    {
         myUri = Uri.parse(cImgURL);

    }


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
        if (doIReset == true)
        {
            doIReset = false;
            refresh();
        }

        recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        if (ReadTheDamBook == true)
        {
            //useOld();
            ReadTheDamBook = false;
        }
        view = findViewById(R.id.viewPager);
        tex = findViewById(R.id.Recipe_Name);
        pic = findViewById(R.id.Image_of_Food);
        tab = findViewById(R.id.Tabs);
        TTM = findViewById(R.id.Time);
        }

        /*TTM.setText(recipe.getReadyInMiniutes());
        tex.setText(recipe.getTitle());
        Picasso.get().load(recipe.getImageURL()).into(pic);
        if (usePic == true)
        {
            if (takenPic != null)
            {
                Uri savedImageURI = Uri.parse(takenPic);
                pic.setImageURI(savedImageURI);
            }
            usePic = false;
        }
        else {
            if (myUri == null)
                Picasso.get().load(imgName).into(pic);
            else
                pic.setImageURI(myUri);
        }*/
        /*tab.addTab(tab.newTab().setText("Ingredients"));
        tab.addTab(tab.newTab().setText("Directions"));
        tab.addTab(tab.newTab().setText("Next Recipe"));
        tab.setTabGravity(TabLayout.GRAVITY_FILL);*/


        RecipeStorage storage = new RecipeStorage(getApplicationContext());
        //storage.setOGName(recipe.getTitle());

        final MyTabAdapter adapter = new MyTabAdapter(this,getSupportFragmentManager(), tab.getTabCount(), recipe);

        /*view.setAdapter(adapter);
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
                //refresh();
            }
        });*/

       /* saveRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecipe();
                System.out.println("Saving recipe");
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CustomStorage cs = new CustomStorage(getApplicationContext());
                cs.resetNUM();
                Intent in = new Intent(RecipeActivity.this, MainActivity.class);
                startActivity(in);
            }
        });*/


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
        /*if (ID != null)
        {
            RecipeStorage storage = new RecipeStorage(getApplicationContext());
            storage.setDirections();
            storage.setIngred();
            storage.setImgURL(imgName);
            storage.setRecipeID(ID);
            storage.setRecipeName(RecipeName);
            storage.setTimeAmount(timeToMake);
        }*/
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
    public void useOld()
    {
        RecipeStorage storage = new RecipeStorage(getApplicationContext());
        imgName = storage.getOldImgURL();
        RecipeName = storage.getOldName();
        Recipe_Ingredient_Tab_Fragment.setIngredients(storage.getOldIngred());
        Recipe_Directions_Tab_Fragment.setDirections(storage.getOldDirections());
        timeToMake = storage.getOldTime();
        //ID = storage.getOldID();

    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        RecipeStorage storage = new RecipeStorage(getApplicationContext());
        // should save as long as i have the id
       /* if (ID != null)
        {
            // should prevent rand and next from overwriting the original
            if (RecipeName.toLowerCase().contains(SearchActivity.getSearchedFood()))
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
        }*/
    }
    public static void setDoIReset(){ doIReset = true;}

    private void saveRecipe(){

        user = (User) getIntent().getSerializableExtra("databaseUserr");

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.rebuildDatabase();

        //int id = recipe.getId();//Integer.parseInt(ID);
        int time = recipe.getReadyInMiniutes();

        Recipe recipe = new Recipe(0, RecipeName, time, getBaseURI() + imgName);
        //recipe.setDirections(Recipe_Directions_Tab_Fragment.getDirections());
        //recipe.setIngredients(Recipe_Ingredient_Tab_Fragment.getIngredients());

        Favorite favoite = new Favorite(0 ,recipe);

        user.getFavorites().add(favoite);
        databaseHelper.getDatabase().getUserDao().updateDetails(user);
    }
    public static void setReadTheBook(boolean bool)
    {
        ReadTheDamBook = bool;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        CustomStorage cs = new CustomStorage(getApplicationContext());
        cs.resetNUM();
        SearchingActivity.SA.finish();
    }
    public static void setTakenPio(String s)
    {
        takenPic = s;
        usePic = true;
    }
}
