package com.recipe.recipesearch.ui.APIComunication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.recipe.recipesearch.ui.CustomRecipes.CustomRecipe;
import com.spoonacular.DefaultApi;
import com.spoonacular.client.ApiException;
import com.spoonacular.client.auth.ApiKeyAuth;
import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;

@SuppressLint("NewApi")
public class CreateRecipeCard extends AsyncTask<Void, Void, String>
{
    public static CreateRecipeCard CRC;
    public static String recipeLink;
    @Override
    protected String doInBackground(Void... voids)
    {
        Bitmap bit = BitmapFactory.decodeFile(CustomRecipe.getCImage());
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.PNG, 100, bo);
        byte[] data=bo.toByteArray();
        OkHttpClient client = new OkHttpClient();
        // gathers the data and creates what will be sent in the post
       // String sendTest = "https://api.spoonacular.com/recipes/visualizeRecipe?title=recipe&image="+bit+"&ingredients=Test\\n%20test\\%20testing&instructions=1%20\\n%202%20\\n%203%20\\n%204&readyInMinutes=5&servings=2&mask=ellipseMask&backgroundImage=none&author=John%20Doe&apiKey=46953957ae604aeba07e605696eef0cc";
        DefaultApi apiInstance = new DefaultApi();
        ApiKeyAuth keyStuff = new ApiKeyAuth("creation","apiKey");
        keyStuff.setApiKey("46953957ae604aeba07e605696eef0cc");
        String title = CustomRecipe.getCName(); // String | The title of the recipe.
        File image = new File(CustomRecipe.getCImage()); // File | The binary image of the recipe as jpg.
        String ingredients = CustomRecipe.getCIngred(); // String | The ingredient list of the recipe, one ingredient per line (separate lines with \\\\n).
        String instructions = CustomRecipe.getCDirect(); // String | The instructions to make the recipe. One step per line (separate lines with \\\\n).
        BigDecimal readyInMinutes = new BigDecimal(CustomRecipe.getCTime()); // BigDecimal | The number of minutes it takes to get the recipe on the table.
        BigDecimal servings = new BigDecimal(CustomRecipe.getCServing()); // BigDecimal | The number of servings that you can make from the ingredients.
        String mask = "ellipseMask"; // String | The mask to put over the recipe image (\\\"ellipseMask\\\", \\\"diamondMask\\\", \\\"diamondMask\\\", \\\"starMask\\\", \\\"heartMask\\\", \\\"potMask\\\", \\\"fishMask\\\").
        String backgroundImage = "none"; // String | The background image (\\\"none\\\",\\\"background1\\\", or \\\"background2\\\").
        String author = "author_example"; // String | The author of the recipe.
        String backgroundColor = "#ffffff"; // String | The background color on the recipe card as a hex-string.
        String fontColor = "#333333"; // String | The font color on the recipe card as a hex-string.
        String source = "source_example"; // String | The source of the recipe.
        Object result = null;
        String test = null;
        try {
            result = apiInstance.createRecipeCard(title, image, ingredients, instructions, readyInMinutes, servings, mask, backgroundImage, author, backgroundColor, fontColor, source);
            Object testing = result;
            test = result.toString();
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#createRecipeCard");
            e.printStackTrace();
        }



        return null;
    }
    public static void setRecipeLink(String s){ recipeLink = s;}
    public static String getRecipeLink(){return  recipeLink;}
}
