package com.example.recipesearch.ui.home_search;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.ui.UiHelper;
import com.example.recipesearch.ui.meal_planner.Meal_Planner_Activity;
import com.example.recipesearch.ui.search_result.SearchActivity;
import com.example.recipesearch.ui.user.login.LoginFragment;
import com.example.recipesearch.ui.user.signup.SignUpFragment;

public class HomeSearchFragment extends Fragment {
    private HomeSearchViewModel homeSearchViewModel;
    private EditText et;
    private ImageView iv;
    private Button login, signup;
    private boolean canSwitch = true;

    @Override
    public void onResume() {
        super.onResume();

        // This stops auto switching back to search result fragment
        et.getText().clear();
        canSwitch = true;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeSearchViewModel =
                ViewModelProviders.of(this).get(HomeSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_search, container, false);
        final UiHelper ui = new UiHelper(getFragmentManager());

        //set vars
        iv = root.findViewById(R.id.imageView);
        et = root.findViewById(R.id.search_bar_edit_text);
        login = root.findViewById(R.id.home_button_login);
        signup = root.findViewById(R.id.home_button_signup);


        //Do img color overlay
        imgColorOverlay();
        et.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(getActivity(), SearchActivity.class);
                startActivity(in);
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Switch to the search result activity
                if(canSwitch) {
                    MainActivity m = (MainActivity) getActivity();
                    if (m != null) {
                        m.setMessage(s);
                    }
                    canSwitch = false;
                    Intent in = new Intent(getActivity(), SearchActivity.class);
                    startActivity(in);
                }
            }
        });

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

    private void imgColorOverlay(){
        iv.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
    }
}