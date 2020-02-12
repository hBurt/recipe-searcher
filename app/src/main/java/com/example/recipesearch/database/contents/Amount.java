package com.example.recipesearch.database.contents;

import java.io.Serializable;

public class Amount implements Serializable {
    private double value;
    private String unit;
    private UnitType unitType;

    public Amount(){
        setValue(0);
        setUnit("");
        setUnitType(UnitType.EMPTY);
    }

    public Amount(double value, String unit, String baseName){
        setValue(value);
        setUnit(unit);

        if(baseName.contains("metric")){
            setUnitType(UnitType.METRIC);
        } else {
            setUnitType(UnitType.US);
        }
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public enum UnitType {
        METRIC,
        US,
        EMPTY
    }
}
