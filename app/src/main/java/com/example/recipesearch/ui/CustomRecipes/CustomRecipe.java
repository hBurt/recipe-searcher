package com.example.recipesearch.ui.CustomRecipes;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomRecipe extends AppCompatActivity
{
    private static String CName = null;
    private static String CIngred = null;
    private static String CDirect = null;
    private static String CTime = null;
    private static String CImage = null;
    Bitmap bitGallery;
    OutputStream output;
    private boolean picTaken = false;
    ImageView preview;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recipe);
        EditText NameE = findViewById(R.id.RecipeName);
        EditText TimeE = findViewById(R.id.Time);
        EditText IngredientE = findViewById(R.id.IngredientInput);
        EditText DirectionE = findViewById(R.id.Directions);
        Button ImageE = findViewById(R.id.ImgselectBTN);
         preview = findViewById(R.id.Preview);
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
        ImageE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SettingImg();
            }
        });
        Sub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*if (picTaken == true) {
                    Bitmap bit =((BitmapDrawable)preview.getDrawable()).getBitmap();
                    try {
                        output = openFileOutput("pic", 0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    File filePath = Environment.getExternalStorageDirectory();
                    File dir = new File(filePath.getAbsolutePath() + "/RecipeSearch");
                    dir.mkdir();
                    File file = new File(System.currentTimeMillis() + ".jpg");
                    try {
                        output = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bit.compress(Bitmap.CompressFormat.JPEG, 80, output);
                    try {
                        output.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent pic = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    pic.setData(Uri.fromFile(file));
                    CImage = dir.getPath();
                    picTaken = false;
                }*/
                CustomStorage cStore = new CustomStorage(getApplicationContext());
                cStore.setCDirections(CDirect);
                cStore.setCImgURL(CImage);
                cStore.setCIngred(CIngred);
                cStore.setCRecipeName(CName);
                cStore.setCTimeAmount(CTime);
                Toast.makeText(getApplicationContext(), "Recipe Saved",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static String getPath( Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }
    private void SettingImg() {
        final CharSequence[] options = { "Snap a Pic", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CustomRecipe.this);
        builder.setTitle("Select a Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Snap a Pic"))
                {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    Bitmap bit = (Bitmap) data.getExtras().get("data");
                    preview.setImageBitmap(bit);
                    picTaken = true;
                    try {
                        output = openFileOutput("pic", 0);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File dir = new File(filePath.getAbsolutePath() + "/RecipeSearch");
                    dir.mkdir();
                    File file = new File(System.currentTimeMillis() + ".jpg");
                    try {
                        output = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bit.compress(Bitmap.CompressFormat.JPEG, 80, output);
                    try {
                        output.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent pic = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    pic.setData(Uri.fromFile(file));
                    CImage = dir.getPath();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enable Camera Permission, or Storage Permissions to use",Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == 2)
            {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                Uri uri = data.getData();
                try {
                    Bitmap bitGallery = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    preview.setImageBitmap(bitGallery);
                    Uri selectedImageUri = data.getData( );
                    CImage = getPath(getApplicationContext(), selectedImageUri );
                    Log.d("Created image path", CImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Storage Permissions to use",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
            else {

            }
        }
    }
}
