package com.example.recipesearch.ui.favorites;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.recipesearch.R;
import com.example.recipesearch.database.Favorite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomAdapter implements ListAdapter {
    ArrayList<Favorite> arrayList;
    Context context;

    public CustomAdapter(Context context, ArrayList<Favorite> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

            TextView tittle = convertView.findViewById(R.id.title);
            final ImageView imageView = convertView.findViewById(R.id.profile_image);

            Picasso.with(context).load(Uri.parse(favorite.getRecipe().getImageURL())).placeholder(R.drawable.image_placeholder_blue).into(imageView);

            String itemName = favorite.getRecipe().getTitle();

            if(itemName.length() > 35){
                String title = itemName.substring(0, 35);
                tittle.setText(title.concat("..."));
            } else{
                tittle.setText(itemName);
            }
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
}
