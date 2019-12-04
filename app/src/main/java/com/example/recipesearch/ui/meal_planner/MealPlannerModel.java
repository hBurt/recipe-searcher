package com.example.recipesearch.ui.meal_planner;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MealPlannerModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MealPlannerModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favorites fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}