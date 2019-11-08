package com.ryan.darkskyweatherapp.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/*
DailyWeatherDao is an interface that represents all queries made to the "daily" table
 */
@Dao
public interface DailyWeatherDao {
    @Query("SELECT * FROM daily WHERE weatherId = 0")
    Single<List<DailyWeather>> selectAll();

    @Query("DELETE FROM daily")
    Completable deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<DailyWeather> dailyWeather);
}
