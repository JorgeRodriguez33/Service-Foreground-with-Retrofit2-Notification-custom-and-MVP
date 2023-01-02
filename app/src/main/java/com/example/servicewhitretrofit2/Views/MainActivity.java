package com.example.servicewhitretrofit2.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.servicewhitretrofit2.Models.ServiceRequestModel.DataWeatherRequest;
import com.example.servicewhitretrofit2.Presenters.MainPresent;
import com.example.servicewhitretrofit2.Presenters.Services.ForegroundService;
import com.example.servicewhitretrofit2.R;
import com.example.servicewhitretrofit2.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private MainPresent _mainPresent;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewBinding = binding.getRoot();
        setContentView(viewBinding);
        _mainPresent = new MainPresent(this);
        clickEvent();

        if (!foregroundServiceRunning()) {
            //start service
            Intent serviceIntent = new Intent(this, ForegroundService.class);
            startService(serviceIntent);
        }

    }

    private void clickEvent() {
        binding.requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.requestButton.setEnabled(false);
                _mainPresent.requestDataFromServer();
            }
        });
    }

    public void onFinishedRequest(DataWeatherRequest weather) {
        binding.linearLayoutInfoWeather.setVisibility(View.VISIBLE);
        binding.cityName.setText(weather.getData().get(0).getCityName());
        binding.pres.setText(weather.getData().get(0).getPres().toString());
        binding.solarRad.setText(weather.getData().get(0).getSolarRad().toString());
        binding.windDir.setText(weather.getData().get(0).getWindDir().toString());
        binding.windSpd.setText(weather.getData().get(0).getWindSpd().toString());

        DataWeatherRequest weather2 = weather;
    }

    public void onFailureRequest(Throwable t) {
        binding.requestButton.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public void onFinishedWithError(String error) {
        binding.requestButton.setEnabled(true);
        Snackbar.make(getWindow().getDecorView().getRootView(), error, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public boolean foregroundServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            //i check if service is running
            if (ForegroundService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}