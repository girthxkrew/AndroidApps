package com.ryan.darkskyweatherapp.Retrofit.RetrofitClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ryan.darkskyweatherapp.Retrofit.POJO.WeatherInfo;
import com.ryan.darkskyweatherapp.RoomDatabase.CurrentWeather;
import com.ryan.darkskyweatherapp.RoomDatabase.CurrentWeatherDao;
import com.ryan.darkskyweatherapp.RoomDatabase.DailyWeather;
import com.ryan.darkskyweatherapp.RoomDatabase.DailyWeatherDao;
import com.ryan.darkskyweatherapp.RoomDatabase.Weather;
import com.ryan.darkskyweatherapp.RoomDatabase.WeatherDatabase;
import com.ryan.darkskyweatherapp.Utility.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.ryan.darkskyweatherapp.Utility.Constants.API_KEY;

/*
The DarkSky Repository handles all network and database calls.
 */

public class DarkSkyRepository {

    private final String TAG = DarkSkyRepository.class.getSimpleName();
    private DarkSkyAPIClient darkSkyAPIClient;
    private MutableLiveData<Weather> weatherData = new MutableLiveData<>();
    private CurrentWeatherDao currentWeatherDao;
    private DailyWeatherDao dailyWeatherDao;
    private Context context;

    public DarkSkyRepository(Context context) {
        darkSkyAPIClient = DarkSkyRetrofitInstance.getRetrofitInstance().create(DarkSkyAPIClient.class);
        currentWeatherDao = WeatherDatabase.getDatabase(context).getCurrentWeatherDao();
        dailyWeatherDao = WeatherDatabase.getDatabase(context).getDailyWeatherDao();
        this.context = context;
    }

    /*
    getWeather() retrieves information from the DarkSkyAPI based on the user's latitude and longitude
    and stores the information into a Room database
     */
    public MutableLiveData<Weather> getWeather(String latitude, String longitude) {
        Single<WeatherInfo> weatherInfoSingle = darkSkyAPIClient.getWeatherData(API_KEY, latitude, longitude);
        weatherInfoSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<WeatherInfo>() {

                    @Override
                    public void onSuccess(WeatherInfo weatherInfo) {
                        Weather weather = new Weather(UtilityMethods.convertCurrentWeatherData(weatherInfo),
                                UtilityMethods.convertDailyWeatherData(weatherInfo));
                        weatherData.setValue(weather);
                        updateWeatherData(weather);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getWeather() error:" + e.getMessage());
                        getWeatherFromDatabase();
                    }
                });
        return weatherData;
    }


    /*
    updateWeatherData() updates the Room database with the most recent retrofit data that was queried.
     */
    public void updateWeatherData(Weather weather) {
        CurrentWeather current = weather.getCurrentWeather();
        List<DailyWeather> daily = weather.getDailyWeather();
        Completable deleteAll = currentWeatherDao.deleteAll();
        Completable insertCurrentWeather = currentWeatherDao.insert(current);
        Completable insertDailyWeather = dailyWeatherDao.insert(daily);
        deleteAll
                .andThen(insertCurrentWeather)
                .andThen(insertDailyWeather)
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "updateWeatherData() was successful!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "updateWeather() error: " + e.getMessage());
                    }
                });
    }

    /*
    isConnected() checks if the user has an network connection
     */
    public boolean isConnected() {
        boolean isConnectedWifi = false;
        boolean isConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    isConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    isConnectedMobile = true;
        }
        return isConnectedWifi || isConnectedMobile;
    }

    /*
    getWeatherDatabase retrieves the last information stored in the database
     */
    public MutableLiveData<Weather> getWeatherFromDatabase() {
        Single<List<DailyWeather>> dailyWeatherSingle = dailyWeatherDao.selectAll();
        Single<CurrentWeather> currentWeatherSingle = currentWeatherDao.getCurrentWeather();
        dailyWeatherSingle.zipWith(currentWeatherSingle,
        new BiFunction<List<DailyWeather>, CurrentWeather, Weather>() {
            @Override
            public Weather apply(List<DailyWeather> dailyWeatherList, CurrentWeather currentWeather) throws Exception {
                return new Weather(currentWeather, dailyWeatherList);
            }
        }).subscribeOn(Schedulers.io())
        .subscribe(new DisposableSingleObserver<Weather>() {
            @Override
            public void onSuccess(Weather weather) {
                weatherData.postValue(weather);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getMessage());
            }
        });
        return weatherData;
    }
}
