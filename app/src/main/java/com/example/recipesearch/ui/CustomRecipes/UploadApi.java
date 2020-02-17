package com.example.recipesearch.ui.CustomRecipes;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadApi
{
    @Multipart
    @POST("/upload")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part Bk,@Part MultipartBody.Part file,@Part MultipartBody.Part ingredients,@Part MultipartBody.Part directions
    ,@Part MultipartBody.Part mask,@Part MultipartBody.Part Rim,@Part MultipartBody.Part serving,@Part MultipartBody.Part title);

}
