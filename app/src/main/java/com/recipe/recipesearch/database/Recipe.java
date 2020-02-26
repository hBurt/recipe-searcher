
package com.recipe.recipesearch.database;


import com.recipe.recipesearch.database.contents.Ingredient;
import com.recipe.recipesearch.database.contents.Step;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private int id;
    private String title, imageURL, baseImageURI, fullURL, directions;
    private int readyInMinutes;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;

    private boolean loaded = false;

    public Recipe() {
        setId(0);
        setTitle("");
        setReadyInMiniutes(0);
        setImageURL("");
        setDirections("");
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public Recipe(int id, String title, int readyInMinutes, String imageURL) {
        setId(id);
        setTitle(title);
        setReadyInMiniutes(readyInMinutes);
        setImageURL(imageURL);
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMiniutes() {
        return readyInMinutes;
    }

    public void setReadyInMiniutes(int readyInMiniutes) {
        this.readyInMinutes = readyInMiniutes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        if(imageURL.contains("https://spoonacular.com/recipeImages/")){
            setBaseImageURI("https://spoonacular.com/recipeImages/");
            this.imageURL = imageURL.replace("https://spoonacular.com/recipeImages/", "");
            System.out.println("[Recipe [SetImageUrl]]" + imageURL);
        } else if(imageURL.contains("https://spoonacular.com/cdn/")){
            setBaseImageURI("https://spoonacular.com/cdn/");
            this.imageURL = imageURL.replace("https://spoonacular.com/cdn/", "");
        } else {
            this.imageURL = imageURL;
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredients(Ingredient ingredient){
        getIngredients().add(ingredient);
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getBaseImageURI() {
        return baseImageURI;
    }

    public void setBaseImageURI(String baseImageURI) {
        this.baseImageURI = baseImageURI;
    }

    public String getFullURL() {
        setFullURL(getBaseImageURI() + getImageURL());
        return fullURL;
    }

    public void setFullURL(String string){
        this.fullURL = string;
    }

    public String display() {

        StringBuilder bui = new StringBuilder();

        for(int i = 0; i < ingredients.size(); i++){
            bui.append("\n");
            bui.append(ingredients.get(i).display());
        }

        StringBuilder buiS = new StringBuilder();

        for(int i = 0; i < steps.size(); i++){
            buiS.append("\n");
            buiS.append(steps.get(i).display());
        }

        return
                "ID: " + getId()
                        + " TITLE: " + getTitle()
                        + " READY IN MINUTES:" + getReadyInMiniutes()
                        + " BASE URI: " + getBaseImageURI()
                        + " IMAGE URL: " + getImageURL()
                        + " FULL URL: " + getFullURL()
                        + " INGREDIENTS: " + bui.toString()
                        + " \nSTEPS: " + buiS.toString();
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
