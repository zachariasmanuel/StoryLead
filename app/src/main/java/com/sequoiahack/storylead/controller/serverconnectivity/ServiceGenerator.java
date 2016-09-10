package com.sequoiahack.storylead.controller.serverconnectivity;


import com.jakewharton.retrofit.Ok3Client;
import com.sequoiahack.storylead.app.AppConstant;

import okhttp3.OkHttpClient;
import retrofit.RestAdapter;

/**
 * Service Generator for retrofit
 * Created by zac on 11/09/16.
 */
public class ServiceGenerator {

    public static String API_BASE_URL = AppConstant.CURRENT_SERVER;

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setClient(new Ok3Client(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}