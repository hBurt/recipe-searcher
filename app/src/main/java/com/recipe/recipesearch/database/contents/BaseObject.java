package com.recipe.recipesearch.database.contents;

import java.io.Serializable;

public class BaseObject implements Serializable {

    private int id;
    private String image, name;

    public BaseObject(){
        setId(-1);
        setImage("");
        setName("");
    }

    public BaseObject(int id, String image, String name) {
        setId(id);
        setImage(image);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
