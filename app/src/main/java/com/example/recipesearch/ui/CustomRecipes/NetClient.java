package com.example.recipesearch.ui.CustomRecipes;

import android.content.Context;
import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class NetClient {
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/visualizeRecipe";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitClient(NetClient context) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    private void uploadToServer(String filePath) {
        Retrofit retrofit = NetClient.getRetrofitClient(this);
        UploadApi uploadAPIs = retrofit.create(UploadApi.class);
        //Create a file object using file path
        File file = new File(filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part Bk = MultipartBody.Part.createFormData("backgroundImage", "none");
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);
        MultipartBody.Part key = MultipartBody.Part.createFormData("apiKey", "46953957ae604aeba07e605696eef0cc");
        MultipartBody.Part ingred = MultipartBody.Part.createFormData("ingredients", CustomRecipe.getCIngred());
        MultipartBody.Part direction = MultipartBody.Part.createFormData("instructions", CustomRecipe.getCDirect());
        MultipartBody.Part mask = MultipartBody.Part.createFormData("mask", "ellipseMask");
        MultipartBody.Part searving = MultipartBody.Part.createFormData("servings", CustomRecipe.getCServing());
        MultipartBody.Part title = MultipartBody.Part.createFormData("title", CustomRecipe.getCName());
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
        //
        Call call = uploadAPIs.uploadImage(image, description);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response)
            {
                Log.d("Success", "File uploaded");
            }
            @Override
            public void onFailure(Call call, Throwable t)
            {
                Log.d("Failure", "Failed to upload");
            }
        });
    }

}
