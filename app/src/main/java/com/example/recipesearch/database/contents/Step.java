package com.example.recipesearch.database.contents;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {

    private int number;
    private String stepInstruction;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<Equipment> equipment = new ArrayList<>();

    public Step(){
        setNumber(-1);
        setStepInstruction("");
    }

    public Step(int number, String stepInstruction){
        setNumber(number);
        setStepInstruction(stepInstruction);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStepInstruction() {
        return stepInstruction;
    }

    public void setStepInstruction(String stepInstruction) {
        this.stepInstruction = stepInstruction;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    public String display(){

        StringBuilder bui = new StringBuilder();
        StringBuilder buiE = new StringBuilder();

        for (Ingredient ingredient : getIngredients()) {
            bui.append("\n");
            bui.append(ingredient.display());
        }

        for(Equipment equi : getEquipment()){
            buiE.append("\n");
            buiE.append(equi.display());
        }

        return "\nSTEP #" + getNumber()
                + "\n" + getStepInstruction()
                + " INGREDIENTS: " + bui.toString()
                + " EQUIPMENT: " + buiE.toString();

    }
}
