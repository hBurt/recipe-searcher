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
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import retrofit2.http.Multipart;

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

        return null;
    }
    public static void setRecipeLink(String s){ recipeLink = s;}
    public static String getRecipeLink(){return  recipeLink;}
}
