package com.example.recipesearch.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.R;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        LinearLayout ll = root.findViewById(R.id.fav_frag_linearLayout);

        /*Button b = new Button(this.getContext());
        b.setText("Button");
        b.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        ll.addView(b);
        ll.addView(b);
        ll.addView(b);
        ll.addView(b);*/

        return root;
    }
}