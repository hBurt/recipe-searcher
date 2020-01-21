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
import com.example.recipesearch.helpers.DatabaseHelper;
import com.example.recipesearch.helpers.UiHelper;
import com.example.recipesearch.ui.home_search.HomeSearchFragment;
import com.example.recipesearch.ui.user.signup.SignUpFragment;

public class LoginFragment extends Fragment {

    private Button login, signup;
    private EditText et_user, et_pass;
    private UiHelper ui;

    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        ui = new UiHelper(getFragmentManager());

        login = root.findViewById(R.id.login_button_login);
        signup = root.findViewById(R.id.login_button_signup);
        et_user = root.findViewById(R.id.edit_text_user);
        et_pass = root.findViewById(R.id.edit_text_pass);

        databaseHelper = ((MainActivity) getActivity()).getDatabaseHelper();

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
                login();
            }
        });

        return root;
    }

    private void login(){
        if(databaseHelper.login(et_user.getText().toString(), et_pass.getText().toString()))
            ((MainActivity) getActivity()).setBottomNavigationVisibility(View.VISIBLE);
            ui.switchScreen(new HomeSearchFragment());
    }

    @Override
    public void onResume() {
        super.onResume();

        et_user.setText("");
        et_pass.setText("");
    }
}
