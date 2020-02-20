package com.recipe.recipesearch.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.recipesearch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.recipe.recipesearch.database.Favorite;
import com.recipe.recipesearch.database.LocalLoginDatabase;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.database.encryption.FactoryPBKDF2;
import com.recipe.recipesearch.ui.home_search.HomeSearchFragment;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class DatabaseHelper {

    private LocalLoginDatabase database;
    private static User currentUser;
    private Activity activity;
    private FactoryPBKDF2 encrypt;

    SharedPreferences sharedPref;
    FirebaseFirestore firestoreDB;
    private FirebaseAuth mAuth;


    public DatabaseHelper(Activity activity){

        setActivity(activity);
        encrypt = new FactoryPBKDF2();
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        firestoreDB = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
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

    public void addCurrentUserToFirestore(){

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference refRoot = mDatabase.child("users");
        String key = refRoot.push().getKey();

        User u = getCurrentUser();
        u.setUid(key);

        firestoreDB.collection("users").document(u.getUid()).set(u)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + u.getUid()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }

    public void createUserWithFirestore(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            addCreatedUserToFirestore(user);
                            //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(DatabaseHelper.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void addCreatedUserToFirestore(FirebaseUser user){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference refRoot = mDatabase.child("users");
        String key = refRoot.push().getKey();

        firestoreDB.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Created user added with id: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }

    public void loginUserInFirestore(String email, String password, UiHelper ui){

        firestoreDB.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String passwordFromDB = (String) document.get("password");
                            Log.d(TAG, "password: " + password);

                            try {
                                if(encrypt.DoDecryption(password, passwordFromDB)){

                                    User user = document.toObject(User.class);
                                    setCurrentUser(user);

                                    Log.d(TAG, "ID: " + user.getId() + " EMAIL: " + user.getEmail());

                                    HomeSearchFragment home = new HomeSearchFragment();
                                    home.setShowLoginMessage(true);
                                    ui.switchScreen(home);

                                }
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (InvalidKeySpecException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        login(email, password);
                    }
        });
    }

    public void updateCurrentUser(boolean newUser){
        updateUser(getCurrentUser());

        User user;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference refRoot = mDatabase.child("users");
        user = getCurrentUser();

        Log.d(TAG,  " before update recipe# count: " + user.getFavorites().size());

        if(newUser) {
            String key = refRoot.push().getKey();
            user.setUid(key);
            setCurrentUser(user);

            Log.d(TAG, user.display());

            firestoreDB.collection("users").document(user.getUid()).set(user)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + getCurrentUser().getUid()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
        }
    }

    public void updateCurrentUser(boolean newUser, Favorite favorite){

        updateUser(getCurrentUser());

        User user;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference refRoot = mDatabase.child("users");
        user = getCurrentUser();

        Log.d(TAG,  " before update recipe# count: " + user.getFavorites().size());
        user.getFavorites().add(favorite);
        getDatabase().getUserDao().updateDetails(user);
        Log.d(TAG,  " before update 2 recipe# count: " + user.getFavorites().size());

        if(newUser) {
            String key = refRoot.push().getKey();
            user.setUid(key);
            setCurrentUser(user);

            Log.d(TAG, user.display());

            firestoreDB.collection("users").document(user.getUid()).set(user)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + getCurrentUser().getUid()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
        } else {
            Log.d(TAG, "Update existing: " + user.display());
            firestoreDB.collection("users").document(user.getUid()).set(user)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "Updated user with UID: " + user.getUid() + " recipe# count: " + user.getFavorites().size()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
        }


        /*DatabaseReference mDatabase;
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();

       Task<QuerySnapshot> tqs = firestoreDB.collection("users").get();

       tqs.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful()) {
                   for (QueryDocumentSnapshot document : task.getResult()) {

                       User user = document.toObject(User.class);

                       if(user.getEmail().matches(getCurrentUser().getEmail())){

                           Log.d(TAG, document.getData().values().toArray().toString());
                           //document.getData().values().toArray().toString();
                           //user.setUser(getCurrentUser());
                       }
                   }
               } else {
                   Log.d(TAG, "Error getting documents: ", task.getException());
               }

           }
       });*/

    }

    public boolean checkLoginState(){
        return currentUser != null;
    }

}
