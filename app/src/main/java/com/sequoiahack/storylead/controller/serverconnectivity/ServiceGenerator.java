package com.sequoiahack.storylead.controller.serverconnectivity;


import com.jakewharton.retrofit.Ok3Client;

import okhttp3.OkHttpClient;
import retrofit.RestAdapter;

/**
 * Service Generator for retrofit
 * Created by zac on 11/09/16.
 */
public class ServiceGenerator {
    public String API_BASE_URL;
    private RestAdapter.Builder builder;

    public ServiceGenerator(String API_BASE_URL) {
        this.API_BASE_URL = API_BASE_URL;
        builder = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new Ok3Client(new OkHttpClient()));
    }

    public <S> S createService(Class<S> serviceClass) {
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}