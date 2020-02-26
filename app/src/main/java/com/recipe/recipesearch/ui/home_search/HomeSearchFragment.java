package com.recipe.recipesearch.ui.home_search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.recipe.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.recipe.recipesearch.helpers.DatabaseHelper;
import com.recipe.recipesearch.helpers.UiHelper;
import com.recipe.recipesearch.ui.MealPlanMaker.MealPlanActivity;
import com.recipe.recipesearch.ui.meal_planner.Meal_Planner_Activity;
import com.recipe.recipesearch.ui.search_result.SearchActivity;
import com.recipe.recipesearch.ui.user.login.LoginFragment;
import com.recipe.recipesearch.ui.user.signup.SignUpFragment;

public class HomeSearchFragment extends Fragment {
    private HomeSearchViewModel homeSearchViewModel;
    private EditText et;
    private ImageView iv;
    private Button login, logout, signup, planer, MPGen;
    private TextView textView_or, textViewUserEmail;
    private Toolbar toolbarUser;
    private boolean canSwitch = true;
    private boolean canWatch = false;
    private boolean performedOnce = false;
    private DatabaseHelper databaseHelper;
    private ConstraintLayout constraintLayout;

    private String userEmail = null;

    private boolean showLoginMessage, loginSucess;

    public HomeSearchFragment(){
    }
    @Override
    public void onResume() {
        super.onResume();

        setButtonVisibilty();

        canWatch = false;
        et.getText().clear();
        canWatch = true;
        setBottomVisibilityAndMargin();
        textViewUserEmail.setText(databaseHelper.getSharedPrefEmail());
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
        logout = root.findViewById(R.id.button_logout);
        signup = root.findViewById(R.id.home_button_signup);
        planer = root.findViewById(R.id.Planer);
        textView_or = root.findViewById(R.id.textView_or2);
        MPGen = root.findViewById(R.id.MPGenerator);

        textViewUserEmail = root.findViewById(R.id.textView_user_email);
        toolbarUser = root.findViewById(R.id.toolbar_user);

        constraintLayout = root.findViewById(R.id.serch_frag);

        databaseHelper = ((MainActivity) getActivity()).getDatabaseHelper();

        if(!performedOnce) {
            performedOnce = true;
            String email = databaseHelper.getSharedPrefEmail();
            String pass = databaseHelper.getSharedPrefPass();
            databaseHelper.loginUserInFirestore(email, pass, ui, true);
        }

        //Do img color overlay
        imgColorOverlay();
        setButtonVisibilty();

        if(databaseHelper.getCurrentUser() == null){
            toolbarUser.setVisibility(View.INVISIBLE);
            textViewUserEmail.setVisibility(View.INVISIBLE);
            logout.setVisibility(View.INVISIBLE);
        } else{
            toolbarUser.setVisibility(View.VISIBLE);
            textViewUserEmail.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
            textViewUserEmail.setText(userEmail);
        }

        logout.setOnClickListener(view -> {
            databaseHelper.logout(ui);
        });

        et.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(getActivity(), SearchActivity.class);
                in.putExtra("databaseUser", databaseHelper.getCurrentUser());
                startActivity(in);
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(canWatch) {
                    canSwitch = true;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(canSwitch && canWatch) {
                    canSwitch = false;
                    String temp = s.toString();
                    Log.v("HomeSearchFrag", "Temp string(on change): " + temp);
                    Intent in = new Intent(getActivity(), SearchActivity.class);
                    in.putExtra("databaseUser", databaseHelper.getCurrentUser());
                    in.putExtra("stringTransfer", temp);
                    canWatch = false;
                    startActivity(in);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Switch to the search result activity
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
        planer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Meal_Planner_Activity.class);
                startActivity(in);
            }
        });
        MPGen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(getActivity(), MealPlanActivity.class);
                startActivity(in);
            }
        });

        setBottomVisibilityAndMargin();


        if(showLoginMessage){

            showLoginMessage();
        }

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //Do nothing
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);
        return root;
    }

    private void imgColorOverlay(){
        iv.setColorFilter(getResources().getColor(R.color.secondary_whiteLightAlpha), PorterDuff.Mode.SRC_ATOP);
    }

    private boolean loginCheck(){
        return ((MainActivity) getActivity()).getDatabaseHelper().getCurrentUser() != null;
    }

    private void setButtonVisibilty(){
        login.setVisibility(loginCheck() ? View.INVISIBLE : View.VISIBLE);
        signup.setVisibility(loginCheck() ? View.INVISIBLE : View.VISIBLE);
        textView_or.setVisibility(loginCheck() ? View.INVISIBLE : View.VISIBLE);
    }

    private void setBottomVisibilityAndMargin(){
        if(databaseHelper.loginCheck()){
            ((MainActivity) getActivity()).setBottomNavigationVisibility(View.VISIBLE);
            setBottomMargin(60);
        } else {
            ((MainActivity) getActivity()).setBottomNavigationVisibility(View.INVISIBLE);
            setBottomMargin(0);
        }
    }

    private void setBottomMargin(int bottomMargin){
        FrameLayout.LayoutParams newLayoutParams = (FrameLayout.LayoutParams) constraintLayout.getLayoutParams();
        newLayoutParams.bottomMargin = bottomMargin;
        constraintLayout.setLayoutParams(newLayoutParams);
    }

    public void setShowLoginMessage(boolean showLoginMessage) {
        this.showLoginMessage = showLoginMessage;
    }

    public void showLoginMessage(){
        Toast toast = Toast.makeText(getContext(), "Login success: " + databaseHelper.getCurrentUser().getEmail(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 50);
        toast.show();
    }

    public void setTextViewUserEmail(String textViewUserEmail){
        userEmail = textViewUserEmail;
    }
}