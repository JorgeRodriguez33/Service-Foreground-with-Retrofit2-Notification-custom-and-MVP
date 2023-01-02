package com.example.servicewhitretrofit2.Presenters;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.example.servicewhitretrofit2.Interfaces.IMainPresenter;
import com.example.servicewhitretrofit2.Models.ServiceRequestModel.DataWeatherRequest;
import com.example.servicewhitretrofit2.Models.ServiceRequestModel.WeatherRequest;
import com.example.servicewhitretrofit2.R;
import com.example.servicewhitretrofit2.Views.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import java.text.DecimalFormat;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;

public class MainPresent implements IMainPresenter, LocationListener {
    private MainActivity _MainActivityView;
    private WeatherRequest _WeatherRequest;
    protected LocationManager locationManager;
    private FusedLocationProviderClient client;
    private Location _location;
    boolean checkGPS = false;
    boolean checkNetwork = false;
    boolean canGetLocation = false;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing latitude.
    public static final String LAT_KEY = "latitude_key";
    // key for storing longitude.
    public static final String LON_KEY = "longitude_key";
    // key for storing urlRequest.
    public static final String URL_REQUEST_KEY = "urlRequest_key";
    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    Location loc;

    public MainPresent(MainActivity _MainActivityView) {
        this._MainActivityView = _MainActivityView;
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this._MainActivityView);
        _WeatherRequest = new WeatherRequest(this);
        getLocation();


        // save latitude and longitude in sharedpreferences
        sharedpreferences = this._MainActivityView.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if(loc != null){
        DecimalFormat df = new DecimalFormat("#.##");
        double lon = Double.valueOf(df.format(loc.getLongitude()));
        double lan = Double.valueOf(df.format(loc.getLatitude()));
        editor.putString(LAT_KEY, String.valueOf(lan));
        editor.putString(LON_KEY, String.valueOf(lon));
        editor.putString(URL_REQUEST_KEY, this._MainActivityView.getResources().getString(R.string.URL));
        // to save key and value.
        editor.apply();
        }
    }

    @Override
    public void requestDataFromServer() {
        String urlRequest = this._MainActivityView.getResources().getString(R.string.URL);
        locationManager = (LocationManager) this._MainActivityView.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this._MainActivityView, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this._MainActivityView, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getLocation();

        if(loc != null){
            DecimalFormat df = new DecimalFormat("#.##");
            double lon = Double.valueOf(df.format(loc.getLongitude()));
            double lan = Double.valueOf(df.format(loc.getLatitude()));
            _WeatherRequest.getWeather(urlRequest, lon, lan);
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this._MainActivityView, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public void onFinished(DataWeatherRequest weather) {
        _MainActivityView.onFinishedRequest(weather);
    }

    public void onFinishedWithError(String error) {
        _MainActivityView.onFinishedWithError(error);
    }

    public void onFailure(Throwable t) {
        _MainActivityView.onFailureRequest(t);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    private Location getLocation() {

        try {
            locationManager = (LocationManager) this._MainActivityView
                    .getSystemService(LOCATION_SERVICE);

            // get GPS status
            checkGPS = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // get network provider status
            checkNetwork = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!checkGPS && !checkNetwork) {
                Toast.makeText(this._MainActivityView, "No Service Provider is available", Toast.LENGTH_SHORT).show();
            } else {
                this.canGetLocation = true;

                // if GPS Enabled get lat/long using GPS Services
                if (checkGPS) {

                    if (ActivityCompat.checkSelfPermission(this._MainActivityView, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this._MainActivityView, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        loc = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loc;
    }

}
