package com.example.recipesearch.ui.recipe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipesearch.R;


public class Recipe_Directions_Tab_Fragment extends Fragment
{
    static String newText = " ";
    public Recipe_Directions_Tab_Fragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe__directions__tab_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        TextView directions = view.findViewById(R.id.Directions);
        // test and example text
        if (newText.length() > 2)
            directions.setText(newText);
        else
            directions.setText("Place the beef cubes in a bowl. Add the minced garlic, pepper and 3 tbsps. of olive oil. Mix well. Cover and keep in the fridge for a couple of hours.Heat the butter and olive oil in a wide shallow pan — wide enough to contain the beef cubes in a single layer. The heat should be very high.Place the beef in a plastic freezer bag. Add the flour. Shake to coat each piece of meat with flour. It is the flour that will thicken the sauce later and make it stick well to the beef.When the olive oil and butter are hot, add the floured beef, spreading the pieces so that every piece touches the oil. Do not stir for a minute or so to allow the underside to brown. Keep the heat very high. Stir and cook for a few minutes, with occasional stirring, until the beef changes color and a light crust forms.Pour in the Worcestershire sauce and liquid seasoning. Stir briskly; the sauce should thicken quite fast. Add the mushrooms, cook just until heated, stirring occasionally.The actual cooking should take no more than five minutes. If you overcook the beef, the meat will turn tough and dry.Transfer the beef salpicao to a serving platter, sprinkle with toasted garlic bits and finely sliced onion leaves. Serve hot with rice.");
    }
    public static void setDirections(String string)
    {
        newText = string;
    }
}
