package com.recipe.recipesearch.ui.CustomRecipes;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.recipe.recipesearch.R;
import com.recipe.recipesearch.ui.APIComunication.CreateRecipeCard;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class CustomRecipe extends AppCompatActivity
{
    private static String CName = null;
    private static String CIngred = null;
    private static String CDirect = null;
    private static String CTime = null;
    private static String CImage = null;
    private static String CServing = null;
    private static boolean CBool = false;
    String currImgPath = null;
    private Uri mImageUri;
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
            public void afterTextChanged(Editable s)
            {
                String regexStr = "^[0-9]*$";
                if(TimeE.getText().toString().trim().matches(regexStr))
                {
                    CTime = s.toString();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(), "Please enter a Number for Time",Toast.LENGTH_SHORT).show();
                    }
            }
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
                CustomStorage cStore = new CustomStorage(getApplicationContext());
                if (CTime == null)
                {
                    CTime = "30";
                }
                cStore.createRecipe(CName, CDirect, CIngred, CTime, CImage, CBool);
                Toast.makeText(getApplicationContext(), "Recipe: "+ cStore.getCount()+" Saved of 25",Toast.LENGTH_LONG).show();
                //askToMakeCard(); // scrapped as it just keeps not working, so the personal storage will be more then 10 to compensate
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
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    {
                        takePic();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Enable Camera Permission, and/or Storage Permissions to use",Toast.LENGTH_SHORT).show();
                    }
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
    private void askToMakeCard() {
        final CharSequence[] options = { "Yes", "NO" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CustomRecipe.this);
        builder.setTitle("Would you like to make a custom Recipe Card?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Yes"))
                {
                    CreateRecipeCard CRC = new CreateRecipeCard();
                    CRC.execute();
                    Toast.makeText(getApplicationContext(), "Link available in the Notes section",Toast.LENGTH_SHORT).show();
                }
                else if (options[item].equals("No")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void takePic()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            File imgFile = null;
            try {
                imgFile = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (imgFile != null)
            {
                Uri imgUri = FileProvider.getUriForFile(this , "com.example.android.fileprovider",imgFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                startActivityForResult(takePictureIntent, 1);
                CBool = true;
            }
        }
        startActivityForResult(takePictureIntent, 1);
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
                    String title = System.currentTimeMillis()+"_pic";
                    String yourDescription = " ";
                    CImage = MediaStore.Images.Media.insertImage(getContentResolver(),BitmapFactory.decodeFile(currImgPath) , title , yourDescription);
                    Bitmap bit = BitmapFactory.decodeFile(currImgPath);
                    preview.setImageBitmap(bit);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enable Camera Permission, and/or Storage Permissions to use",Toast.LENGTH_SHORT).show();
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
                    CBool = false;
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
    private File getImageFile() throws IOException
    {
        String time = String.valueOf(System.currentTimeMillis());
        String imgName = "pic_"+ time+"_";
        File storeDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imgFile = File.createTempFile(imgName, ".jpg", storeDir);
        currImgPath = imgFile.getAbsolutePath();
        return imgFile;
    }
    public static String getCName(){return CName;}
    public static String getCIngred(){return CIngred;}
    public static String getCDirect(){return CDirect; }
    public static String getCTime(){return CTime;}
    public static String getCImage(){return CImage;}
    public static String getCServing()
    {
        if (CServing != null)
            return CServing;
        else
            return "4";
    }
}