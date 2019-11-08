package com.ryan.darkskyweatherapp.RoomDatabase;

import java.util.List;

public class Weather {
    /*
    Weather class is used to easily store both results from the database queries and send them to methods
    that need the information
     */
    private CurrentWeather currentWeather;
    private List<DailyWeather> dailyWeather;

    public Weather(CurrentWeather currentWeather, List<DailyWeather> dailyWeather) {
        this.currentWeather = currentWeather;
        this.dailyWeather = dailyWeather;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public List<DailyWeather> getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(List<DailyWeather> dailyWeather) {
        this.dailyWeather = dailyWeather;
    }
}
