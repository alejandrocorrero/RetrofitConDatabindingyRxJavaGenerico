package com.example.a2dam.retrofit.api;

import com.example.a2dam.retrofit.api.responses.PeopleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SwapiService {
    @GET("people")
    List<Call<PeopleResponse>> getPeoples();
}
