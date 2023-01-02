package com.example.servicewhitretrofit2.Interfaces;

import com.example.servicewhitretrofit2.Models.DatumWeather;
import com.example.servicewhitretrofit2.Models.ServiceRequestModel.DataWeatherRequest;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IWeatherApi {

    //https://rapidapi.com/weatherbit/api/weather

    ///https://weatherbit-v1-mashape.p.rapidapi.com/current?lon={lon}&lat={lat}
    @Headers({
            "X-RapidAPI-Host: weatherbit-v1-mashape.p.rapidapi.com",
            "X-RapidAPI-Key: 3387f2e852msh7ab0635a216d4cdp19c65cjsn9ad555796ee7"
    })
    @GET("current?")
    Call<DataWeatherRequest> getWeather(@Query("lon")double lon, @Query("lat")double lat);

}
