package com.recipe.recipesearch.ui.user.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.recipe.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.recipe.recipesearch.helpers.DatabaseHelper;
import com.recipe.recipesearch.helpers.UiHelper;
import com.recipe.recipesearch.ui.home_search.HomeSearchFragment;
import com.recipe.recipesearch.ui.user.signup.SignUpFragment;

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

    private void login(){
       // if(databaseHelper.login(et_user.getText().toString(), et_pass.getText().toString()))
        databaseHelper.loginUserInFirestore(et_user.getText().toString(), et_pass.getText().toString(), ui, false);

        if(databaseHelper.checkLoginState()) {
            ((MainActivity) getActivity()).setBottomNavigationVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        et_user.setText("");
        et_pass.setText("");
    }
}
