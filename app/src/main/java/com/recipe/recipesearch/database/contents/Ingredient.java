package com.recipe.recipesearch.database.contents;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Ingredient extends BaseObject implements Serializable {

    private ArrayList<Amount> amounts;

    public Ingredient(){
        super();
        setAmount(new ArrayList<Amount>());
        setBaseimage("https://spoonacular.com/cdn/ingredients_100x100/");
    }

    public Ingredient(String name, String image){
        setName(name);
        setImage(image);
        setBaseimage("https://spoonacular.com/cdn/ingredients_100x100/");
    }

    public Ingredient(String name, String image, ArrayList<Amount> amounts) {
        setName(name);
        setImage(image);
        setAmount(amounts);
        setBaseimage("https://spoonacular.com/cdn/ingredients_100x100/");
    }

    @Override
    public void setImage(String image) {
            super.setImage(image);
    }

    public String getFullImageURL(){
        Log.v("Ingredient", getBaseimage() + getImage());
        return getBaseimage() + getImage();
    }

    public ArrayList<Amount> getAmount() {
        return amounts;
    }

    public void setAmount(ArrayList<Amount> amounts) {
        this.amounts = amounts;
    }

    public String display(){
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("NAME: ");
        builder.append(getName());
        builder.append(" IMAGE: ");
        builder.append(getImage());
        builder.append(" AMOUNT -> ");
        if(getAmount().size() > 0) {
            for (int j = 0; j < 2; j++) {
                builder.append(getAmount().get(j).getValue());
                builder.append(" ");
                builder.append(getAmount().get(j).getUnit());
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}