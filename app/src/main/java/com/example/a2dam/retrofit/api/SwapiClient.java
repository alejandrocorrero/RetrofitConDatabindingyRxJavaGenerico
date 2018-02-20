package com.example.a2dam.retrofit.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SwapiClient {
    private static SwapiClient ourInstance;
    private final SwapiService service;

    public static synchronized SwapiClient getInstance() {
        if (ourInstance == null) {
            ourInstance = new SwapiClient();
        }
        return ourInstance;
    }

    private SwapiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(SwapiService.class);
    }

    public SwapiService getService() {
        return service;
    }
}
