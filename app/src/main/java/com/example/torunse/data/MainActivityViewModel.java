package com.example.torunse.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    //survive orientation changes
    public MutableLiveData< boolean > isOn = new MutableLiveData<boolean>();
}
