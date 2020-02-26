package com.recipe.recipesearch.ui.favorites;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.recipe.recipesearch.FavoriteRecipeView;
import com.recipe.recipesearch.R;
import com.recipe.recipesearch.database.Favorite;
import com.recipe.recipesearch.helpers.UiHelper;
import com.recipe.recipesearch.FavoriteRecipeView;
import com.recipe.recipesearch.R;
import com.recipe.recipesearch.database.Favorite;
import com.recipe.recipesearch.database.User;
import com.recipe.recipesearch.helpers.UiHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter implements ListAdapter {
    ArrayList<Favorite> arrayList;
    Context context;
    ListView listView;
    UiHelper uiHelper;
    User user;

    public CustomAdapter(Context context, ArrayList<Favorite> arrayList, ListView listView, UiHelper uiHelper, User user) {
        this.arrayList = arrayList;
        this.context = context;
        this.listView = listView;
        this.uiHelper = uiHelper;
        this.user = user;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    public Favorite getFavoriteAtIndex(int index){ return arrayList.get(index); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public void removeItemAtIndex(int i){
        arrayList.remove(i);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Favorite favorite = arrayList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_row, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("clicked index: " + position);
                    System.out.println("fav title: " + favorite.getRecipe().getTitle() + " fav img: " + favorite.getRecipe().getImageURL() + " direct: " + favorite.getRecipe().getDirections() + " ingred: " + favorite.getRecipe().getIngredients());
                    uiHelper.switchScreen(new FavoriteRecipeView(favorite, user));
                }
            });

            loadImageIntoView(convertView, favorite);

            concatTitle(convertView, favorite.getRecipe().getTitle());
            colorizeRatings(convertView, favorite);

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private void setRatingStarColor(ImageView imageView){
        imageView.setColorFilter(ContextCompat.getColor(context, R.color.secondary_yellow), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    private void concatTitle(View view, String itemTitle){
        TextView favoriteTitle = view.findViewById(R.id.title);

        if(itemTitle.length() > 35){
            String title = itemTitle.substring(0, 35);
            favoriteTitle.setText(title.concat("..."));
        } else{
            favoriteTitle.setText(itemTitle);
        }
    }

    private void colorizeRatings(View view, Favorite favorite){
        ArrayList<ImageView> ratings = new ArrayList<>();

        ratings.add(view.findViewById(R.id.rating_1));
        ratings.add(view.findViewById(R.id.rating_2));
        ratings.add(view.findViewById(R.id.rating_3));
        ratings.add(view.findViewById(R.id.rating_4));
        ratings.add(view.findViewById(R.id.rating_5));

        for (int i = 0; i < 5; ++i) {
            if(i < favorite.getRating()){
                setRatingStarColor(ratings.get(i));
            }
        }
    }

    private void loadImageIntoView(View view, Favorite favorite){
        ImageView profileImageView = view.findViewById(R.id.profile_image);
        Picasso.get().load(Uri.parse(favorite.getRecipe().getFullURL())).placeholder(R.drawable.image_placeholder_blue).into(profileImageView);
    }

    public void refreshItems()
    {
        ArrayList<Favorite> copy = new ArrayList<>(arrayList);
        this.arrayList.clear();
        this.arrayList.addAll(copy);
        notifyDataSetChanged();
    }

}
