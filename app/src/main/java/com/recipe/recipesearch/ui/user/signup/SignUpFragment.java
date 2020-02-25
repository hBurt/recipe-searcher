package com.recipe.recipesearch.ui.user.signup;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.recipe.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.recipe.recipesearch.database.Favorite;
import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.database.encryption.FactoryPBKDF2;
import com.recipe.recipesearch.helpers.DatabaseHelper;
import com.recipe.recipesearch.helpers.UiHelper;
import com.recipe.recipesearch.ui.home_search.HomeSearchFragment;
import com.recipe.recipesearch.ui.user.login.LoginFragment;

import java.util.ArrayList;

public class SignUpFragment extends Fragment {

    private Button login, signup;
    private EditText email, password, passwordConfirm;
    private TextView error_email, error_password;

    DatabaseHelper databaseHelper;

    private FirebaseAuth mAuth;

    private enum errorMessage { EMAIL, PASSWORD }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        final UiHelper ui = new UiHelper(getFragmentManager());

        //Declare vars
        login = root.findViewById(R.id.signup_button_login);
        signup = root.findViewById(R.id.signup_button_signup);

        email = root.findViewById(R.id.signup_edit_text_email);
        password = root.findViewById(R.id.signup_edit_text_pass);
        passwordConfirm = root.findViewById(R.id.signup_edit_text_pass_confirm);

        error_email = root.findViewById(R.id.signup_error_email);
        error_password = root.findViewById(R.id.signup_error_pass_nomatch);

        error_email.setVisibility(View.INVISIBLE);
        error_password.setVisibility(View.INVISIBLE);

        databaseHelper = ((MainActivity) getActivity()).getDatabaseHelper();
        mAuth = FirebaseAuth.getInstance();

        //Listeners
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ui.switchScreen(new LoginFragment());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSignupValid(isEmailValid(), isPasswordValid())){
                    addUserToDatabase();
                    databaseHelper.addCurrentUserToFirestore();

                    ((MainActivity) getActivity()).setBottomNavigationVisibility(View.VISIBLE);

                    databaseHelper.loginUserInFirestore(email.getText().toString(), password.getText().toString(), ui, false);
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showErrorMessage(errorMessage.EMAIL);
            }
        });

        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                showErrorMessage(errorMessage.PASSWORD);
            }
        });

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                ui.switchScreen(new HomeSearchFragment());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        email.setText("");
        password.setText("");
        passwordConfirm.setText("");

    }

    private void populateListWithHardcodedItems(ArrayList<Favorite> list){

        Recipe thaiSweetPotato = new Recipe();
        thaiSweetPotato.setTitle("Thai Sweet Potato");
        thaiSweetPotato.setImageURL("https://spoonacular.com/recipeImages/thai-sweet-potato-veggie-burgers-with-spicy-peanut-sauce-262682.jpg");

        Recipe cajunSpiced = new Recipe();
        cajunSpiced.setTitle("Cajun Spiced Black Bean and Sweet Potato Burgers");
        cajunSpiced.setImageURL("https://spoonacular.com/recipeImages/Cajun-Spiced-Black-Bean-and-Sweet-Potato-Burgers-227961.jpg");

        list.add(new Favorite(5, thaiSweetPotato));
        list.add(new Favorite(5, cajunSpiced));
        list.add(new Favorite(5, thaiSweetPotato));
        list.add(new Favorite(4, cajunSpiced));
        list.add(new Favorite(4, thaiSweetPotato));
        list.add(new Favorite(4, cajunSpiced));
        list.add(new Favorite(3, thaiSweetPotato));
        list.add(new Favorite(3, cajunSpiced));
        list.add(new Favorite(3, thaiSweetPotato));
        list.add(new Favorite(2, cajunSpiced));
        list.add(new Favorite(2, thaiSweetPotato));
        list.add(new Favorite(2, cajunSpiced));
        list.add(new Favorite(1, thaiSweetPotato));
        list.add(new Favorite(1, cajunSpiced));
        list.add(new Favorite(1, thaiSweetPotato));
    }

    private boolean isSignupValid(boolean emailIsValid, boolean passwordIsValid){

        return emailIsValid && passwordIsValid;
    }

    private void addUserToDatabase(){

        final FactoryPBKDF2 encrypt = new FactoryPBKDF2();

        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(encrypt.DoEncrption(password.getText().toString().toCharArray()));
        ((MainActivity) getActivity()).getDatabaseHelper().addUser(user);

        databaseHelper.setCurrentUser(user);
    }

    private void showErrorMessage(errorMessage errorMessageType){

        switch (errorMessageType){
            case EMAIL:
                setEmailErrorVisibility();
                break;
            case PASSWORD:
                setPasswordErrorVisibility();
                break;
        }
    }

    private boolean isEmailValid(){

        String emailString = email.getText().toString();

        return checkEmailAtSign(emailString)
                && checkEmailDot(emailString)
                && checkEmailLengthAfterDot(emailString);
    }

    private boolean checkEmailAtSign(String s){

        return s.length() >= 1 && s.contains("@");
    }

    private boolean checkEmailDot(String s) {

        String[] emailStringSplit = s.split("@");

        return emailStringSplit.length > 1 && emailStringSplit[1].contains(".");
    }

    private boolean checkEmailLengthAfterDot(String s){
        String[] emailStringSplit = s.split("@");
        String[] emailStringSplitDot = emailStringSplit[1].split("[.]");

        return emailStringSplitDot.length > 1 && emailStringSplitDot[0].length() > 1;
    }

    private void setEmailErrorVisibility(){
        if(isEmailValid() || email.getText().length() == 0){
            error_email.setVisibility(View.INVISIBLE);
        } else {
            error_email.setVisibility(View.VISIBLE);
        }
    }

    private void setPasswordErrorVisibility(){
        if(password.getText().toString().matches(passwordConfirm.getText().toString())){
            error_password.setVisibility(View.INVISIBLE);
        } else {
            error_password.setVisibility(View.VISIBLE);
        }
    }

    private boolean isPasswordValid(){

        //If error_pass is invisible, password is valid
        return error_password.getVisibility() == View.INVISIBLE;
    }
    
    
}
