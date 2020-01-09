package com.example.recipesearch.ui.user.login;

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
import com.example.recipesearch.database.User;
import com.example.recipesearch.database.encryption.FactoryPBKDF2;
import com.example.recipesearch.ui.UiHelper;
import com.example.recipesearch.ui.home_search.HomeSearchFragment;
import com.example.recipesearch.ui.search_result.SearchFragment;
import com.example.recipesearch.ui.user.signup.SignUpFragment;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class LoginFragment extends Fragment {

    private Button login, signup;
    private EditText et_user, et_pass;
    private List<User> users;
    private User currUser;

    private FactoryPBKDF2 encrypt;
    private UiHelper ui;

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

        final MainActivity main = (MainActivity) getParentFragment().getActivity();

        users = main.returnUsers();

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
                if(userExistsAndValid()){
                    main.setCurrentUser(currUser);
                }
            }
        });

        return root;
    }

    private boolean userExistsAndValid(){
        boolean doesUserExistAndValid= false;

        for (User u : users) {
            if(u.getEmail().matches(et_user.getText().toString())){
                try {
                    if(encrypt.DoDecryption(et_pass.getText().toString(), u.getPassword())){
                        doesUserExistAndValid = true;
                        currUser = u;

                        System.out.println(u.getEmail() + " : logged in");
                        ui.switchScreen(new HomeSearchFragment());
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }
            }
        }

        return doesUserExistAndValid;
    }
}
