package com.example.recipesearch.ui.APIComunication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.recipesearch.ui.CustomRecipes.CustomRecipe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

//import com.spoonacular.client.ApiException;
//import com.spoonacular.DefaultApi;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        String sendTest = "https://api.spoonacular.com/recipes/visualizeRecipe?title=recipe&image="+bit+"&ingredients=Test\\n%20test\\%20testing&instructions=1%20\\n%202%20\\n%203%20\\n%204&readyInMinutes=5&servings=2&mask=ellipseMask&backgroundImage=none&author=John%20Doe&apiKey=46953957ae604aeba07e605696eef0cc";
        com.squareup.okhttp.Request request = new Request.Builder()
                .url(sendTest) // will use only the first result
                .build();

        Response response = null; // needs an id num or will cause an error
        String responseData = null;
        try {
            response = client.newCall(request).execute(); // provides a val for the first search and preforms it
            responseData = response.body().string();
            String test = responseData;

        } catch (IOException e) {
        }
        return null;
    }
    public static void setRecipeLink(String s){ recipeLink = s;}
    public static String getRecipeLink(){return  recipeLink;}
}
