package com.recipe.recipesearch.api;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BackgroundRequest extends AsyncTask<Void, Void, String> {

    private static final String TAG = "BackgroundRequest";
    private String requstString;
    private SearchType type;
    private OkHttpClient client;
    private Response responseBody;
    private String toReturn = null;

    Context context;

    private RequestType requestType;

    private int id;

    protected AsyncResponse delegate = null;

    public BackgroundRequest(String requestString, SearchType type, Context context) {
        this.requstString = requestString;
        this.type = type;
        this.context = context;
        requestType = RequestType.REQUEST_BASE_RECIPE;
        client = new OkHttpClient();
        client.setReadTimeout(15, TimeUnit.SECONDS);
        client.setConnectTimeout(10, TimeUnit.SECONDS);
    }

    public BackgroundRequest(int id, SearchType type, Context context){
        this.id = id;
        this.type = type;
        this.context = context;
        requestType = RequestType.REQUEST_BASE_RECIPE_ID;
        client = new OkHttpClient();
        client.setReadTimeout(15, TimeUnit.SECONDS);
        client.setConnectTimeout(10, TimeUnit.SECONDS);
    }

    public BackgroundRequest(int id, RequestType requestType) {
        this.id = id;
        this.requestType = requestType;
        client = new OkHttpClient();
        client.setReadTimeout(15, TimeUnit.SECONDS);
        client.setConnectTimeout(10, TimeUnit.SECONDS);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(Void... voids) {

        Request request = null;

        URLHandler urlHandler = new URLHandler(requstString, type);

        switch (requestType){

            case REQUEST_BASE_RECIPE:
                request = new Request.Builder()
                        .url(urlHandler.buildUrl())
                        .build();
                break;
            case REQUEST_BASE_RECIPE_ID:
                request = new Request.Builder()
                        .url(urlHandler.buildUrlForSimilar(id))
                        .build();
                break;
            case REQUEST_INGREDIENTS:
                request = new Request.Builder()
                        .url(urlHandler.buildUrlForIngredients(id))
                        .build();
                break;
            case REQUEST_INSTRUCTIONS:
                request = new Request.Builder()
                        .url(urlHandler.buildUrlForInstructions(id))
                        .build();
                break;
        }

        try {
            responseBody = client.newCall(request).execute();
            toReturn = responseBody.body().string();
            Log.v(TAG, toReturn);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        delegate.processFinish(toReturn);
    }

    private void printLong(String string) {
        int maxLogSize = 1000;
        for (int i = 0; i <= string.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > string.length() ? string.length() : end;
            Log.v(TAG, string.substring(start, end));
        }
    }

    public enum SearchType {
        RECIPE,
        RANDOM,
        NEXT//, INGREDIENT
    }

    public enum RequestType {
        REQUEST_BASE_RECIPE,
        REQUEST_BASE_RECIPE_ID,
        REQUEST_INGREDIENTS,
        REQUEST_INSTRUCTIONS
    }
}
