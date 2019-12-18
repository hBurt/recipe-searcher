package com.example.recipesearch.ui.user.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recipesearch.R;
import com.example.recipesearch.ui.UiHelper;
import com.example.recipesearch.ui.user.signup.SignUpFragment;

public class LoginFragment extends Fragment {

    private Button login, signup;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        final UiHelper ui = new UiHelper(getFragmentManager());

        login = root.findViewById(R.id.login_button_login);
        signup = root.findViewById(R.id.login_button_signup);

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

        return root;
    }
}
