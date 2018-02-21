package com.example.a2dam.retrofit.api;


import com.example.a2dam.retrofit.api.responses.PeopleResponse;
import com.example.a2dam.retrofit.api.responses.Planet;
import com.example.a2dam.retrofit.api.responses.Result;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SwapiService {
    @GET("people")
    Observable<PeopleResponse> getPeoples();
    @GET("planets/{id}")
    Observable<Planet> getPlanet(@Path("id") int planet);
}
