package com.ryan.darkskyweatherapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ryan.darkskyweatherapp.Retrofit.RetrofitClient.DarkSkyRepository;
import com.ryan.darkskyweatherapp.RoomDatabase.Weather;

/*
WeatherViewModel is the View Model that observes the UI and updates it when new information is available
 */

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<Weather> mutableLiveData;
    private DarkSkyRepository darkSkyRepository;

    public void init(String latitude, String longitude, Context context){
        if (mutableLiveData != null){
            return;
        }
        darkSkyRepository = new DarkSkyRepository(context);
        if(darkSkyRepository.isConnected())
        {
            mutableLiveData = darkSkyRepository.getWeather(latitude, longitude);
        }
        else
        {
            mutableLiveData = darkSkyRepository.getWeatherFromDatabase();
        }

    }

    public LiveData<Weather> getDarkSkyRepository() {
        return mutableLiveData;
    }

}
