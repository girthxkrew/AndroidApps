package com.ryan.darkskyweatherapp.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/*
CurrentWeatherDao is an interface that represents all class made to the table named "current"
 */
@Dao
public interface CurrentWeatherDao {

    @Query("SELECT * FROM current WHERE id = 0")
    Single<CurrentWeather> getCurrentWeather();

    @Query("Delete FROM current")
    Completable deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(CurrentWeather currentWeather);

}
