package com.example.a2dam.retrofit.api;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SwapiClient {
    private static SwapiClient ourInstance;
    private final SwapiService service;

    public static synchronized SwapiClient getInstance(Context contxt) {
        if (ourInstance == null) {
            ourInstance = new SwapiClient(contxt);
        }
        return ourInstance;
    }

    private SwapiClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ChuckInterceptor(context));

        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(SwapiService.class);
    }

    public SwapiService getService() {
        return service;
    }
}
