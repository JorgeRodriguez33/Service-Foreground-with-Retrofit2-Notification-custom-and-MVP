package com.example.servicewhitretrofit2.Presenters.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {

        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())//Se especifica el convertidor de JSON que necesitamos (Gson)
                    .build();

        }

        return retrofit;

    }

}
