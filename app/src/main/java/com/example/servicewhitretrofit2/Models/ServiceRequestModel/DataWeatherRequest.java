package com.example.servicewhitretrofit2.Models.ServiceRequestModel;

import com.example.servicewhitretrofit2.Models.DatumWeather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataWeatherRequest {

    @SerializedName("count")
    @Expose
    private double count;

    @SerializedName("data")
    @Expose
    private List<DatumWeather> data;

    public DataWeatherRequest(int count, List<DatumWeather> data) {
        this.count = count;
        this.data = data;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public List<DatumWeather> getData() {
        return data;
    }

    public void setData(List<DatumWeather> data) {
        this.data = data;
    }
}
