package com.recipe.recipesearch.ui.recipe;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.recipesearch.R;
import com.recipe.recipesearch.database.Recipe;
import com.recipe.recipesearch.database.contents.Amount;
import com.recipe.recipesearch.database.contents.Ingredient;
import com.squareup.picasso.Picasso;

public class IngredientAdapter extends BaseAdapter implements ListAdapter {

    private Recipe recipe;
    private Context context;

    public IngredientAdapter(Context context, Recipe recipe){
        setRecipe(recipe);
        setContext(context);
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
        return getRecipe().getIngredients().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    public Ingredient getRecipeeAtIndex(int index){ return getRecipe().getIngredients().get(index); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public void removeItemAtIndex(int i){
        getRecipe().getIngredients().remove(i);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Ingredient ingredient = getRecipeeAtIndex(i);
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_ingredient, viewGroup, false);

        }

        loadImageIntoView(view, ingredient);

        setTitle(view, ingredient.getName());
        setAmount(view, ingredient.getAmount().get(1));

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getRecipe().getIngredients().size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void loadImageIntoView(View view, Ingredient ingredient){
        ImageView profileImageView = view.findViewById(R.id.profile_image_ingredient);
        String img = ingredient.getFullImageURL();
        Picasso.get().load(img).placeholder(R.drawable.image_placeholder_blue).into(profileImageView);
    }

    private void setTitle(View view, String title){
        TextView ingredientTitle = view.findViewById(R.id.textViewIngredientName);
        ingredientTitle.setText(title);
    }

    private void setAmount(View view, Amount amount){
        TextView ingredientAmout = view.findViewById(R.id.textViewIngredientAmount);
        String amt = amount.getValue() + " " + amount.getUnit();
        ingredientAmout.setText(amt);
    }
}
