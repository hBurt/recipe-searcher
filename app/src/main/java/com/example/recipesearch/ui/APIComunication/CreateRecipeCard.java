package com.example.recipesearch.ui.APIComunication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.Settings;

import com.example.recipesearch.database.User;
import com.example.recipesearch.ui.CustomRecipes.CustomRecipe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
        InputStreamBody inputStreamBody = new InputStreamBody(new ByteArrayInputStream(data), CustomRecipe.getCImage());
        OkHttpClient client = new OkHttpClient();
        // gathers the data and creates what will be sent in the post
        MultipartEntityBuilder mEntity = MultipartEntityBuilder.create();
        mEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        mEntity.addTextBody("backgroundImage", "none");
        mEntity.addPart("img_file", inputStreamBody);
        mEntity.addTextBody("ingredients", CustomRecipe.getCIngred());
        mEntity.addTextBody("instructions", CustomRecipe.getCDirect());
        mEntity.addTextBody("mask", "ellipseMask");
        mEntity.addTextBody("servings", CustomRecipe.getCServing());
        mEntity.addTextBody("title", CustomRecipe.getCName());


        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/visualizeRecipe")
                // will place whole recipe here to send and create the custom card
                .post(null)//(RequestBody) mEntity.build())
                .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "7dba1c8b6dmsh8c3919fbe127d43p122d00jsn89f1b32d2216")
                .addHeader("content-type", "multipart/form-data")
                .build();
        Response response;
        String rData;
        try {
            response = client.newCall(request).execute();
            rData = response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void setRecipeLink(String s){ recipeLink = s;}
    public static String getRecipeLink(){return  recipeLink;}
}
