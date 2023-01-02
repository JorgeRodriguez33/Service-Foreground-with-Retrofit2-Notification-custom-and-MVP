package com.example.servicewhitretrofit2.Presenters.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.example.servicewhitretrofit2.Interfaces.IWeatherApi;
import com.example.servicewhitretrofit2.Models.DatumWeather;
import com.example.servicewhitretrofit2.Models.ServiceRequestModel.DataWeatherRequest;
import com.example.servicewhitretrofit2.Presenters.Network.ApiClient;
import com.example.servicewhitretrofit2.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForegroundService extends Service {

    private static final int NOTIF_ID = 1100;

    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing latitude.
    public static final String LAT_KEY = "latitude_key";
    // key for storing longitude.
    public static final String LON_KEY = "longitude_key";
    // key for storing urlRequest.
    public static final String URL_REQUEST_KEY = "urlRequest_key";
    // variable for shared preferences.
    SharedPreferences sharedpreferences;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ROOM + FOREGROUND", "FOREGROUND");

        //i get latitude and longitude from sharedpreferences
        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        double lat = Double.valueOf(sharedpreferences.getString(LAT_KEY, null));
        double lon = Double.valueOf(sharedpreferences.getString(LON_KEY, null));
        String urlRequest = sharedpreferences.getString(URL_REQUEST_KEY, null);
        final DataWeatherRequest[] weather = new DataWeatherRequest[1];
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                weather[0] = getWeather(urlRequest, lon, lat);
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
        ).start();

        if (weather[0] != null)
            startForeground(
                    NOTIF_ID,
                    getMyActivityNotification(weather[0])
            );

        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification getMyActivityNotification(DataWeatherRequest weather) {
        final String CHANNELID = "Weather ID";

        //implement of notification custom
        RemoteViews collapsedView = new RemoteViews(getPackageName(), R.layout.notification_collapsed);
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notificacion_expanded);
        Intent clickIntent = new Intent(this, BroadcastReceiverWeather.class);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(this, 0, clickIntent, 0);
        NotificationChannel channel = new NotificationChannel(CHANNELID, CHANNELID, NotificationManager.IMPORTANCE_LOW);
        collapsedView.setTextViewText(R.id.text_view_collapsed_title, "I am a Notificacion Title!!!");
        collapsedView.setTextViewText(R.id.text_view_collapsed_info, "I am a Notificacion of Info!!!");

        expandedView.setImageViewResource(R.id.imgWeather, R.drawable.weather);
        expandedView.setOnClickPendingIntent(R.id.imgWeather, clickPendingIntent);
        expandedView.setTextViewText(R.id.text_view_expanded_cityName, weather.getData().get(0).getCityName());
        expandedView.setTextViewText(R.id.text_view_expanded_pres, weather.getData().get(0).getPres().toString());
        expandedView.setTextViewText(R.id.text_view_expanded_solar_rad, weather.getData().get(0).getSolarRad().toString());
        expandedView.setTextViewText(R.id.text_view_expanded_wind_dir, weather.getData().get(0).getWindDir().toString());
        expandedView.setTextViewText(R.id.text_view_expanded_wind_spd, weather.getData().get(0).getWindSpd().toString());
        //****************

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, new Intent(this, ForegroundService.class), 0);

        return new Notification.Builder(this, CHANNELID)
//                .setContentTitle("Weather service")
//                .setContentText(text)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setSmallIcon(R.drawable.ic_cloud)
                .setContentIntent(contentIntent).getNotification();
    }

    public void ThreadNotificacion(DataWeatherRequest weather) {

        Notification notification = getMyActivityNotification(weather);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIF_ID, notification);

    }

    //Get current weather
    public DataWeatherRequest getWeather(String urlRequest, double lon, double lat) {
        DataWeatherRequest[] weather = new DataWeatherRequest[1];
        IWeatherApi _IWeatherApi;
        _IWeatherApi = ApiClient.getClient(urlRequest).create(IWeatherApi.class);
        Call<DataWeatherRequest> call = _IWeatherApi.getWeather(lon, lat);
        call.enqueue(new Callback<DataWeatherRequest>() {
            @Override
            public void onResponse(Call<DataWeatherRequest> call, Response<DataWeatherRequest> response) {
                weather[0] = response.body();
                if (response.code() == 200) {
                    ThreadNotificacion(weather[0]);
                } else if (response.code() == 429) {
                  //limit exceeded...
                    weather[0] = null;
                }
            }

            @Override
            public void onFailure(Call<DataWeatherRequest> call, Throwable t) {

            }

        });
        return weather[0];

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
