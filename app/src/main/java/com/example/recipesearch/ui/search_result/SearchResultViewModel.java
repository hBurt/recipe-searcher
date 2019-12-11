package com.example.recipesearch.ui.search_result;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class SearchResultViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

    public SearchResultViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("Place Holder Result");
    }
    public LiveData<String> getText()
    {
        return mText;
    }
}