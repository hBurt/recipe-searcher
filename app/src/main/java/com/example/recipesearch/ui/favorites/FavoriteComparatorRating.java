package com.example.recipesearch.ui.favorites;

import com.example.recipesearch.database.Favorite;

import java.util.Comparator;

public class FavoriteComparatorRating implements Comparator<Favorite> {

    boolean isReversed;

    FavoriteComparatorRating(boolean isReversed){
        this.isReversed = isReversed;
    }

    @Override
    public int compare(Favorite favoriteA, Favorite favoriteB) {

        int rating = 0;

            if(!isReversed) {
                rating = favoriteA.getRating() - favoriteB.getRating();
            } else {
                rating = favoriteB.getRating() - favoriteA.getRating();
            }

        return rating;
    }
}
