package com.example.recipesearch.ui.favorites;

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

import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter implements ListAdapter {
    ArrayList<Favorite> arrayList;
    Context context;
    ListView listView;

    public CustomAdapter(Context context, ArrayList<Favorite> arrayList, ListView listView) {
        this.arrayList = arrayList;
        this.context = context;
        this.listView = listView;
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
        Favorite favorite = arrayList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_row, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
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
        imageView.setColorFilter(ContextCompat.getColor(context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN);
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

        ratings.add((ImageView) view.findViewById(R.id.rating_1));
        ratings.add((ImageView) view.findViewById(R.id.rating_2));
        ratings.add((ImageView) view.findViewById(R.id.rating_3));
        ratings.add((ImageView) view.findViewById(R.id.rating_4));
        ratings.add((ImageView) view.findViewById(R.id.rating_5));

        for (int i = 0; i < 5; ++i) {
            if(i < favorite.getRating()){
                setRatingStarColor(ratings.get(i));
            }
        }
    }

    private void loadImageIntoView(View view, Favorite favorite){
        ImageView profileImageView = view.findViewById(R.id.profile_image);
        Picasso.get().load(Uri.parse(favorite.getRecipe().getImageURL())).placeholder(R.drawable.image_placeholder_blue).into(profileImageView);
    }

    public void refreshItems()
    {
        ArrayList<Favorite> copy = new ArrayList<>(arrayList);
        this.arrayList.clear();
        this.arrayList.addAll(copy);
        notifyDataSetChanged();
    }

}
