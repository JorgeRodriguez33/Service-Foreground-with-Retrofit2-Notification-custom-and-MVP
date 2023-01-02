package com.example.servicewhitretrofit2.Models.ServiceRequestModel;

import com.example.servicewhitretrofit2.Interfaces.IMainPresenter;
import com.example.servicewhitretrofit2.Interfaces.IWeatherApi;
import com.example.servicewhitretrofit2.Models.DatumWeather;
import com.example.servicewhitretrofit2.Presenters.MainPresent;
import com.example.servicewhitretrofit2.Presenters.Network.ApiClient;
import com.example.servicewhitretrofit2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRequest {

    private MainPresent _maiMainPresent;

    public WeatherRequest(MainPresent _maiMainPresent) {
        this._maiMainPresent = _maiMainPresent;
    }

    //Get current weather from
    public void getWeather(String urlRequest, double lon,double lat ) {
        IWeatherApi _IWeatherApi;
        _IWeatherApi = ApiClient.getClient(urlRequest).create(IWeatherApi.class);
        Call<DataWeatherRequest> call = _IWeatherApi.getWeather(lon,lat);
        call.enqueue(new Callback<DataWeatherRequest>() {
            @Override
            public void onResponse(Call<DataWeatherRequest> call, Response<DataWeatherRequest> response) {
                DataWeatherRequest weather = response.body();

                if(response.code() == 200){
                _maiMainPresent.onFinished(weather);
                }
                else if(response.code() == 429){
                    List<DatumWeather> l = new ArrayList<>();
                    l.add(new DatumWeather());
                    String message = "";
                    try {
                        message = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    _maiMainPresent.onFinishedWithError(message);
                }
            }
            @Override
            public void onFailure(Call<DataWeatherRequest> call, Throwable t) {
                // Log error here since request failed
                _maiMainPresent.onFailure(t);
            }
        });
    }


}
