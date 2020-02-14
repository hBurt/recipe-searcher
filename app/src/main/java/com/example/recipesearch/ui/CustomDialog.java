package com.example.recipesearch.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.example.recipesearch.database.User;
import com.example.recipesearch.helpers.DatabaseHelper;

import java.util.ArrayList;

public class CustomDialog extends Dialog implements android.view.View.OnClickListener {

    public Activity activity;
    public ImageView star_1, star_2, star_3, star_4, star_5;
    public Button cancel;

    private Favorite favorite;

    ArrayList<ImageView> stars;

    String TAG = "CustomDialog";

    User user;
    DatabaseHelper databaseHelper;

    public CustomDialog(Activity activity, int themeResId, Favorite favorite, User user) {
        super(activity, themeResId);
        this.favorite = favorite;
        this.user = user;
        this.activity = activity;
        stars = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        star_1 = findViewById(R.id.dialog_star_1);
        star_2 = findViewById(R.id.dialog_star_2);
        star_3 = findViewById(R.id.dialog_star_3);
        star_4 = findViewById(R.id.dialog_star_4);
        star_5 = findViewById(R.id.dialog_star_5);

        stars.add(star_1);
        stars.add(star_2);
        stars.add(star_3);
        stars.add(star_4);
        stars.add(star_5);

        cancel = findViewById(R.id.button_dialog_cancel);

        for(ImageView iv : stars){
            iv.setOnClickListener(this);
        }
        cancel.setOnClickListener(this);

        checkRatingAndSetStarColor();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_star_1:
                favorite.setRating(1);
                break;
            case R.id.dialog_star_2:
                favorite.setRating(2);
                break;
            case R.id.dialog_star_3:
                favorite.setRating(3);
                break;
            case R.id.dialog_star_4:
                favorite.setRating(4);
                break;
            case R.id.dialog_star_5:
                favorite.setRating(5);
                break;
            case R.id.button_dialog_cancel:
                Log.v(TAG, "cancel");
                break;
            default:
                break;
        }
        save();
        dismiss();
    }

    private void checkRatingAndSetStarColor(){
        for(int i = 0; i < 5; i++){
            if(favorite.getRating() > i){
                stars.get(i).setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                stars.get(i).setColorFilter(ContextCompat.getColor(getContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
    }

    private void save(){

        databaseHelper = new DatabaseHelper(activity);
        databaseHelper.rebuildDatabase();

        databaseHelper.getDatabase().getUserDao().updateDetails(user);
    }

}