package com.example.a2dam.retrofit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.a2dam.retrofit.api.responses.PeopleResponse;
import com.example.a2dam.retrofit.api.responses.PlanetResponse;

public class MainActivityViewModel extends AndroidViewModel {
    LiveData<PeopleResponse> people;
    LiveData<PlanetResponse> planet;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        Composit
    }
}
