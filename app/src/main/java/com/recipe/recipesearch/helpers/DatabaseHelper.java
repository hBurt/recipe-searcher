package com.recipe.recipesearch.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.recipesearch.R;
import com.recipe.recipesearch.database.LocalLoginDatabase;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.database.encryption.FactoryPBKDF2;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class DatabaseHelper {

    private LocalLoginDatabase database;
    private User currentUser;
    private Activity activity;
    private FactoryPBKDF2 encrypt;

    SharedPreferences sharedPref;

    public DatabaseHelper(Activity activity){

        setActivity(activity);
        encrypt = new FactoryPBKDF2();
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public LocalLoginDatabase getDatabase() {
        return database;
    }

    public void setDatabase(LocalLoginDatabase database) {
        this.database = database;
    }

    private void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<User> returnUsers(){
        return getDatabase().getUserDao().getDetails();
    }

    public void addUser(User user){
        getDatabase().getUserDao().insertDetails(user);
    }

    public void updateUser(User user){
        getDatabase().getUserDao().updateDetails(user);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isUserLoggedOn(){
        return getCurrentUser() != null;
    }

    public void saveLoginState(){
        if(isUserLoggedOn()) {
            SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            //Save e-mail
            editor.putString(activity.getResources().getString(R.string.saved_user), getCurrentUser().getEmail());

            //Save password
            editor.putString(activity.getResources().getString(R.string.saved_pass), getCurrentUser().getPassword());

            //Write data in background
            editor.apply();
        }
    }

    public boolean isLoginStateSaved(){

        return getSharedPrefEmail() != null && getSharedPrefPass() != null;
    }

    public boolean login(String email, String password){
        boolean isValid = false;
        for (User user : returnUsers()) {
            if(user.getEmail().matches(email)){
                try{
                    if(encrypt.DoDecryption(password, user.getPassword())){
                        setCurrentUser(user);
                        System.out.println(user.getEmail() + " : logged in with :: " + user.getFavorites().size() + " favorites");

                        saveLoginState();

                        isValid = true;
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
            }
        }
        return isValid;
    }

    public boolean loginCheck(){
        return getCurrentUser() != null;
    }


    public String getSharedPrefEmail(){

        String defualtEmail = null;

        return sharedPref.getString(activity.getResources().getString(R.string.saved_user), defualtEmail);
    }

    public String getSharedPrefPass(){

        String defaultPass = null;

        return sharedPref.getString(activity.getResources().getString(R.string.saved_pass), defaultPass);
    }

    public void rebuildDatabase(){
        LocalLoginDatabase lld = Room.databaseBuilder(activity.getApplicationContext(), LocalLoginDatabase.class, "LOCAL_LOGIN_DATABASE")
                .allowMainThreadQueries().build();

        setDatabase(lld);
    }

}
