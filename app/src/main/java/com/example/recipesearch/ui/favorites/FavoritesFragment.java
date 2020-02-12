package com.example.recipesearch.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.recipesearch.FavoriteRecipeView;
import com.example.recipesearch.MainActivity;
import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.example.recipesearch.helpers.DatabaseHelper;
import com.example.recipesearch.helpers.UiHelper;

import java.util.ArrayList;
import java.util.Collections;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;

    private ArrayList<Favorite> favoritesList;
    private ArrayList<Favorite> favoritesListSecondary;

    DatabaseHelper databaseHelper;
    private EditText searchText;
    private ListView list;
    private AppCompatImageButton buttonFilterAlphabetical;
    private AppCompatImageButton buttonFilterRating;
    CustomAdapter customAdapter;
    private AdapterView<?> adapterView;

    private Button favoriteButtonDelete;

    private boolean isAlphabetical = false;
    private boolean isRating = false;

    UiHelper uiHelper;

    @Override
    public void onResume() {
        super.onResume();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        uiHelper = new UiHelper(getFragmentManager());

        searchText = root.findViewById(R.id.favorites_search_text);
        databaseHelper = ((MainActivity) getActivity()).getDatabaseHelper();

        buttonFilterAlphabetical = root.findViewById(R.id.favorites_filter_alphabetical);
        buttonFilterRating = root.findViewById(R.id.favorites_filter_rating);

        favoriteButtonDelete = root.findViewById(R.id.favorite_button_delete);

        initFavoritesList(root);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateList(editable.toString());
            }
        });

        buttonFilterAlphabetical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAlphabetical) {
                    Collections.sort(favoritesListSecondary, new FavoriteComparatorAlphabetical(true));
                    isAlphabetical = true;
                    buttonFilterAlphabetical.setImageResource(R.drawable.ic_atoz_24dp);
                } else {
                    Collections.sort(favoritesListSecondary, new FavoriteComparatorAlphabetical(false));
                    isAlphabetical = false;
                    buttonFilterAlphabetical.setImageResource(R.drawable.ic_ztoa_24dp);
                }
                setAdapter(favoritesListSecondary);
            }
        });

        buttonFilterRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRating){
                    Collections.sort(favoritesListSecondary, new FavoriteComparatorRating(false));
                    isRating = true;
                    buttonFilterRating.setImageResource(R.drawable.ic_favoriteup_24dp);
                } else {
                    Collections.sort(favoritesListSecondary, new FavoriteComparatorRating(true));
                    isRating = false;
                    buttonFilterRating.setImageResource(R.drawable.ic_favoritedown_24dp);
                }
                setAdapter(favoritesListSecondary);
            }
        });



        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                setAdapterView(adapterView);

                favoriteButtonDelete = view.findViewById(R.id.favorite_button_delete);

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                );

                favoriteButtonDelete.setLayoutParams(param);
                favoriteButtonDelete.setVisibility(View.VISIBLE);

                ((CustomAdapter) getAdapterView().getAdapter()).refreshItems();

                favoriteButtonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        int index = getAdapterView().getFirstVisiblePosition();
                        View v = getAdapterView().getChildAt(0);
                        int top = (v == null) ? 0 : (v.getTop() - getAdapterView().getPaddingTop());

                        ((CustomAdapter) getAdapterView().getAdapter()).removeItemAtIndex(i);
                        ((CustomAdapter) getAdapterView().getAdapter()).refreshItems();
                        ((CustomAdapter) getAdapterView().getAdapter()).refreshItems();
                        //((CustomAdapter) getAdapterView().getAdapter()).notifyDataSetChanged();

                        setAdapter(favoritesListSecondary);

                        // restore index and position
                        ((ListView) getAdapterView()).setSelectionFromTop(index, top);
                    }
                });
                return false;
            }
        });


       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Favorite currentfavorite = ((CustomAdapter) getAdapterView().getAdapter()).getFavoriteAtIndex(i);

                System.out.println("clicked index: " + i);
                System.out.println("fav title: " + currentfavorite.getRecipe().getTitle() + " fav img: " + currentfavorite.getRecipe().getImageURL());
                /ui.switchScreen(new FavoriteRecipeView(currentfavorite));

            }
        });*/


        return root;


    }

    private void initFavoritesList(View root){
        list = root.findViewById(R.id.list);

        favoritesList = databaseHelper.getCurrentUser().getFavorites();
        favoritesListSecondary = new ArrayList<>(favoritesList);

        if(favoritesList.size() > 0){
            customAdapter = new CustomAdapter(getContext(), favoritesList, list, uiHelper);
            list.setAdapter(customAdapter);
        }
    }

    private void updateList(String searchValue){

        favoritesListSecondary = new ArrayList<>(favoritesList);

        ArrayList<Integer> indexesToRemove = new ArrayList<>();

        for(int i = 0; i < favoritesListSecondary.size(); i++){
            if(!favoritesListSecondary.get(i).getRecipe().getTitle().toLowerCase().contains(searchValue.toLowerCase())){
                indexesToRemove.add(i);
            }
        }

        reversedRemoval(indexesToRemove, favoritesListSecondary);

        setAdapter(favoritesListSecondary);
    }

    private void reversedRemoval(ArrayList<Integer> list, ArrayList<Favorite> favoritesList){
        for(int i = list.size() - 1; i >= 0; --i){

            int removeIndex = list.get(i);
            favoritesList.remove(removeIndex);
        }
    }

    private void setAdapter(ArrayList<Favorite> newList){
        if(newList.size() > 0) {
            CustomAdapter customAdapter = new CustomAdapter(getContext(), newList, list, uiHelper);
            list.setAdapter(customAdapter);
        }
    }

    public AdapterView<?> getAdapterView() {
        return adapterView;
    }

    public void setAdapterView(AdapterView<?> adapterView) {
        this.adapterView = adapterView;
    }
}