package com.example.recipesearch.ui.favorites;

import com.example.recipesearch.database.Favorite;

import java.util.Comparator;

public class FavoriteComparatorAlphabetical implements Comparator<Favorite> {

    boolean isReversed;

    FavoriteComparatorAlphabetical(boolean isReversed){
        this.isReversed = isReversed;
    }

    @Override
    public int compare(Favorite favoriteA, Favorite favoriteB) {

        String compareRecipeTitle = favoriteA.getRecipe().getTitle();

            int alphabetical = favoriteB.getRecipe().getTitle().compareTo(compareRecipeTitle);

            if (alphabetical != 0) {
                if (!isReversed) {
                    return alphabetical;
                } else {

                    return -alphabetical;
                }
            }

        /*int rating = 0;

            if(!isReversed) {
                rating = favoriteA.getRating() - favoriteB.getRating();
            } else {
                rating = favoriteB.getRating() - favoriteA.getRating();
            }*/

        return alphabetical;
    }

}
