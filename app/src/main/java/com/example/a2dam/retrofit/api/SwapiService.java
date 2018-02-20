package com.example.a2dam.retrofit.api;


import com.example.a2dam.retrofit.api.responses.PeopleResponse;
import com.example.a2dam.retrofit.api.responses.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SwapiService {
    @GET("people")
    Observable<PeopleResponse> getPeoples();
}
