package com.example.recipesearch.ui.user.signup;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.database.User;
import com.example.recipesearch.database.encryption.FactoryPBKDF2;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.example.recipesearch.helpers.UiHelper;
import com.example.recipesearch.ui.home_search.HomeSearchFragment;
import com.example.recipesearch.ui.user.login.LoginFragment;

public class SignUpFragment extends Fragment {

    private Button login, signup;
    private EditText email, password, passwordConfirm;
    private TextView error_email, error_password;

    DatabaseHelper databaseHelper;

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
                    if(databaseHelper.login(email.getText().toString(), password.getText().toString()))
                        ((MainActivity) getActivity()).setBottomNavigationVisibility(View.VISIBLE);
                    ui.switchScreen(new HomeSearchFragment());
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
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        email.setText("");
        password.setText("");
        passwordConfirm.setText("");

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
