package com.recipe.recipesearch.database.contents;

import java.io.Serializable;
import java.util.ArrayList;

public class Ingredient extends BaseObject implements Serializable {

    private ArrayList<Amount> amounts;

    private String fullImageURL;

    public Ingredient(){
        super();
        setAmount(new ArrayList<>());
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
            setFullImageURL(getBaseimage() + getImage());
    }

    public String getFullImageURL(){
        return fullImageURL;
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

    public void setFullImageURL(String fullImageURL) {
        this.fullImageURL = fullImageURL;
    }
}