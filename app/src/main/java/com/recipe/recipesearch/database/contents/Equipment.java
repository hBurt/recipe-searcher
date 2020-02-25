package com.recipe.recipesearch.database.contents;

import java.io.Serializable;

public class Equipment extends BaseObject implements Serializable {

    private int temperatureNumber;
    private String temperatureUnit;

    public Equipment(){
        super();
        setTemperatureNumber(-1);
        setTemperatureUnit("");
    }

    public Equipment(int id, String image, String name) {
        super(id, image, name);
        setTemperatureNumber(-1);
        setTemperatureUnit("");
    }

    @Override
    public void setImage(String image) {
        super.setImage("https://spoonacular.com/cdn/equipment_100x100/" + image);
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public int getTemperatureNumber() {
        return temperatureNumber;
    }

    public void setTemperatureNumber(int temperatureNumber) {
        this.temperatureNumber = temperatureNumber;
    }

    public String display(){
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("NAME: ");
        builder.append(getName());
        builder.append(" IMAGE: ");
        builder.append(getImage());
        builder.append(" TEMPERATURE: ");
        builder.append(getTemperatureNumber());
        builder.append(" ");
        builder.append(getTemperatureUnit());

        return builder.toString();
    }
}
