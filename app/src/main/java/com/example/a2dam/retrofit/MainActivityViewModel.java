package com.example.a2dam.retrofit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.a2dam.retrofit.api.SwapiClient;
import com.example.a2dam.retrofit.api.SwapiService;
import com.example.a2dam.retrofit.api.responses.PeopleResponse;
import com.example.a2dam.retrofit.api.responses.Planet;
import com.example.a2dam.retrofit.api.responses.Result;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<List<Result>> people;
    private List<MutableLiveData<Planet>> planet = new ArrayList<>();


    private final SwapiService swapiService;

    LiveData<List<Result>> getPeople() {
        Observable<PeopleResponse> pepo;
        if (people == null) {
            people = new MutableLiveData<>();
            pepo = swapiService.getPeoples();
            pepo.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::setPeople);
        }
        return people;
    }

    // TODO ARREGLAR
    private void setPeople(PeopleResponse peopleResponse) {
        people.setValue(peopleResponse.getResults());

    }

    LiveData<Planet> getPlanet(int planeta, int planetoide) {
        if (planet.size() <= planetoide) {
            planet.add(new MutableLiveData<>());
            swapiService.getPlanet(planeta).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(t -> setPlanet(t, planetoide));
        }
        return planet.get(planetoide);
    }

    private void setPlanet(Planet planeta, int planetoide) {
        planet.get(planetoide).setValue(planeta);
    }

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        swapiService = SwapiClient.getInstance(application.getApplicationContext()).getService();

    }


}