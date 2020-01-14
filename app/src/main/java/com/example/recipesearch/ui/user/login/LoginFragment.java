package com.example.recipesearch.ui.user.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.example.recipesearch.database.User;
import com.example.recipesearch.database.encryption.FactoryPBKDF2;
import com.example.recipesearch.helpers.UiHelper;
import com.example.recipesearch.ui.home_search.HomeSearchFragment;
import com.example.recipesearch.ui.user.signup.SignUpFragment;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class LoginFragment extends Fragment {

    private Button login, signup;
    private EditText et_user, et_pass;
    private List<User> users;
    private FactoryPBKDF2 encrypt;
    private UiHelper ui;

    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        encrypt = new FactoryPBKDF2();
        ui = new UiHelper(getFragmentManager());

        login = root.findViewById(R.id.login_button_login);
        signup = root.findViewById(R.id.login_button_signup);
        et_user = root.findViewById(R.id.edit_text_user);
        et_pass = root.findViewById(R.id.edit_text_pass);

        databaseHelper = ((MainActivity) getActivity()).getDatabaseHelper();

        users = databaseHelper.returnUsers();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ui.switchScreen(new LoginFragment());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ui.switchScreen(new SignUpFragment());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userExistsAndValid();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        et_user.setText("");
        et_pass.setText("");
    }

    private void userExistsAndValid(){

        for (User u : users) {
            if(u.getEmail().matches(et_user.getText().toString())){

                try {
                    if(encrypt.DoDecryption(et_pass.getText().toString(), u.getPassword())){

                        //User logged in
                        databaseHelper.setCurrentUser(u);
                        System.out.println(u.getEmail() + " : logged in with :: " + u.getFavorites().size() + " favorites");

                        saveLoginState();

                        ui.switchScreen(new HomeSearchFragment());

                        //Add & show random favorites
                        /*for(int i = 0; i < 10; ++i)
                            u.getFavorites().add(new Favorite(new Random().nextInt(), new Random().nextInt()));
                        databaseHelper.updateUser(u);

                        //show favorites
                        for(int i = 0; i < u.getFavorites().size(); ++i){
                            Favorite fav = u.getFavorites().get(i);
                            System.out.println("Fav id: " + fav.getId() + ", Fav rating: " + fav.getRating());
                        }*/

                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveLoginState(){
        if(databaseHelper.isUserLoggedOn()) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            //Save e-mail
            editor.putString(getResources().getString(R.string.saved_user), databaseHelper.getCurrentUser().getEmail());

            //Save password
            editor.putString(getResources().getString(R.string.saved_pass), databaseHelper.getCurrentUser().getPassword());

            //Write data in background
            editor.apply();
        }
    }
}
