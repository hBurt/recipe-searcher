package com.example.recipesearch.ui.home_search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeSearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeSearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Recipe\n   Search");
    }



    public LiveData<String> getText() {
        return mText;
    }



}