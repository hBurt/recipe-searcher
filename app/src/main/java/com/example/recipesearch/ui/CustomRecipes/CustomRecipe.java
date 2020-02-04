package com.example.recipesearch.ui.CustomRecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipesearch.R;

public class CustomRecipe extends AppCompatActivity
{
    private static String CName = null;
    private static String CIngred = null;
    private static String CDirect = null;
    private static String CTime = null;
    private static String CImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recipe);
        EditText NameE = findViewById(R.id.RecipeName);
        EditText TimeE = findViewById(R.id.Time);
        EditText IngredientE = findViewById(R.id.IngredientInput);
        EditText DirectionE = findViewById(R.id.Directions);
        TextView ImageE = findViewById(R.id.ImgSelection);
        Button Sub = findViewById(R.id.CSubmit);
        NameE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { CName = s.toString(); }
        });
        TimeE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {CTime = s.toString(); }
        });
        IngredientE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { CIngred = s.toString();}
        });
        DirectionE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { CDirect = s.toString();}
        });
        Sub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CustomStorage cStore = new CustomStorage(getApplicationContext());
                cStore.setCDirections(CDirect);
                //cStore.setCImgURL(CImage);
                cStore.setCIngred(CIngred);
                cStore.setCRecipeName(CName);
                cStore.setCTimeAmount(CTime);
            }
        });

    }

}
