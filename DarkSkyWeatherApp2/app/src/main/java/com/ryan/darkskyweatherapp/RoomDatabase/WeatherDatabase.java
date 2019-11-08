package com.ryan.darkskyweatherapp.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
WeatherDatabase is the database class that room uses to create and access the database
 */
@Database(entities = {CurrentWeather.class, DailyWeather.class}, version = 2)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract CurrentWeatherDao getCurrentWeatherDao();
    public abstract DailyWeatherDao getDailyWeatherDao();

    private static WeatherDatabase INSTANCE;

    public static WeatherDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDatabase.class, "weather")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
