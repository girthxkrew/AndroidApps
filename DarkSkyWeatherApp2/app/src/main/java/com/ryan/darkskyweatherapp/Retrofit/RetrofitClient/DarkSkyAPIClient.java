package com.ryan.darkskyweatherapp.Retrofit.RetrofitClient;

import com.ryan.darkskyweatherapp.Retrofit.POJO.WeatherInfo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
DarkSkyAPI is an interface that handles all DarkSkyAPI calls used in this app
 */
public interface DarkSkyAPIClient {

    @GET("forecast/{API_KEY}/{LATITUDE},{LONGITUDE}?exclude=minutely,hourly,alerts,flags")
    Single<WeatherInfo> getWeatherData(@Path("API_KEY") String apiKey,
                                       @Path("LATITUDE") String latitude, @Path("LONGITUDE") String longitude);
}
