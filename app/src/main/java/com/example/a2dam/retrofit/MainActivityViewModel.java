package com.example.a2dam.retrofit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.a2dam.retrofit.api.responses.Result;

public class MainActivityViewModel extends AndroidViewModel {
    LiveData<Result> people;
    LiveData<Result> planet;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

    }
}
