package com.recipe.recipesearch.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

    public RecipeViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is Recipe fragment");
    }
    public LiveData<String> getText()
    {
        return mText;
    }
}
