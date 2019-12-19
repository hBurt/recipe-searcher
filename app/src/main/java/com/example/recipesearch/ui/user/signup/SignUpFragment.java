package com.example.recipesearch.ui.user.signup;


import android.content.res.Resources;
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

import com.example.recipesearch.R;
import com.example.recipesearch.ui.UiHelper;
import com.example.recipesearch.ui.user.login.LoginFragment;

public class SignUpFragment extends Fragment {

    private Button login, signup;
    private EditText email, password, passwordConfirm;
    private TextView error_email, error_password;
    boolean emailIsValid = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        final UiHelper ui = new UiHelper(getFragmentManager());

        final Resources res = getResources();

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
                if(editable.length() >= 1){

                    String emailString = editable.toString();
                    if(emailString.contains("@")) { // Does email contain '@'
                        String[] emailStringSplit = emailString.split("@");

                        if(emailStringSplit.length > 1 && emailStringSplit[1].contains(".")) { // Does email contain '.'
                            String[] emailStringSplitDot = emailStringSplit[1].split("[.]");

                            if (emailStringSplitDot.length > 1 &&
                                    emailString.contains("@") &&
                                    emailStringSplit[1].contains(".") &&
                                    emailStringSplitDot[0].length() > 1) {
                                emailIsValid = true;
                            } else {
                                emailIsValid = false;
                            }
                        } else {
                            emailIsValid = false;
                        }
                    } else {
                        emailIsValid = false;
                    }
                } else {
                    emailIsValid = false;
                }

                if(emailIsValid || editable.length() == 0){
                    error_email.setVisibility(View.INVISIBLE);
                } else {
                    error_email.setVisibility(View.VISIBLE);
                }
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
                if(password.getText().toString().matches(passwordConfirm.getText().toString())){
                    error_password.setVisibility(View.INVISIBLE);
                } else {
                    error_password.setVisibility(View.VISIBLE);
                }
            }
        });
        return root;
    }

}
