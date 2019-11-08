package com.ryan.darkskyweatherapp.Utility;

import com.ryan.darkskyweatherapp.Retrofit.POJO.WeatherInfo;
import com.ryan.darkskyweatherapp.RoomDatabase.CurrentWeather;
import com.ryan.darkskyweatherapp.RoomDatabase.DailyWeather;

import java.util.ArrayList;
import java.util.List;

/*
UtilityMethods is a class that contains methods that are used between various classes
in the project
 */
public class UtilityMethods {

    public static CurrentWeather convertCurrentWeatherData(WeatherInfo weatherInfo) {
        CurrentWeather current = new CurrentWeather(
                0, weatherInfo.getLatitude(), weatherInfo.getLongitude(), weatherInfo.getTimezone(),
                weatherInfo.getCurrently().getTime(), weatherInfo.getCurrently().getSummary(), weatherInfo.getCurrently().getIcon(),
                weatherInfo.getCurrently().getNearestStormDistance(), weatherInfo.getCurrently().getPrecipIntensity(),
                weatherInfo.getCurrently().getPrecipProbability(), weatherInfo.getCurrently().getTemperature(),
                weatherInfo.getCurrently().getApparentTemperature(), weatherInfo.getCurrently().getDewPoint(),
                weatherInfo.getCurrently().getHumidity(), weatherInfo.getCurrently().getPressure(),
                weatherInfo.getCurrently().getWindSpeed(), weatherInfo.getCurrently().getWindGust(),
                weatherInfo.getCurrently().getWindBearing(), weatherInfo.getCurrently().getCloudCover(),
                weatherInfo.getCurrently().getUvIndex(), weatherInfo.getCurrently().getVisibility(),
                weatherInfo.getCurrently().getOzone());
        return current;
    }

    public static List<DailyWeather> convertDailyWeatherData(WeatherInfo weatherInfo) {
        List<DailyWeather> daily = new ArrayList<>();
        for (int i = 0; i < weatherInfo.getDaily().getData().size(); i++) {
            DailyWeather dailyWeather = new DailyWeather(0, weatherInfo.getDaily().getSummary(), weatherInfo.getDaily().getIcon(),
                    weatherInfo.getDaily().getData().get(i).getTime(), weatherInfo.getDaily().getData().get(i).getSummary(),
                    weatherInfo.getDaily().getData().get(i).getIcon(), weatherInfo.getDaily().getData().get(i).getSunriseTime(),
                    weatherInfo.getDaily().getData().get(i).getSunsetTime(), weatherInfo.getDaily().getData().get(i).getMoonPhase(),
                    weatherInfo.getDaily().getData().get(i).getPrecipIntensity(), weatherInfo.getDaily().getData().get(i).getPrecipIntensityMax(),
                    weatherInfo.getDaily().getData().get(i).getPrecipIntensityMaxTime(), weatherInfo.getDaily().getData().get(i).getPrecipProbability(),
                    weatherInfo.getDaily().getData().get(i).getPrecipType(), weatherInfo.getDaily().getData().get(i).getTemperatureHigh(),
                    weatherInfo.getDaily().getData().get(i).getTemperatureHighTime(), weatherInfo.getDaily().getData().get(i).getTemperatureLow(),
                    weatherInfo.getDaily().getData().get(i).getTemperatureLowTime(), weatherInfo.getDaily().getData().get(i).getApparentTemperatureHigh(),
                    weatherInfo.getDaily().getData().get(i).getApparentTemperatureHighTime(), weatherInfo.getDaily().getData().get(i).getApparentTemperatureLow(),
                    weatherInfo.getDaily().getData().get(i).getApparentTemperatureLowTime(), weatherInfo.getDaily().getData().get(i).getDewPoint(),
                    weatherInfo.getDaily().getData().get(i).getHumidity(), weatherInfo.getDaily().getData().get(i).getPressure(),
                    weatherInfo.getDaily().getData().get(i).getWindSpeed(), weatherInfo.getDaily().getData().get(i).getWindGust(),
                    weatherInfo.getDaily().getData().get(i).getWindGustTime(), weatherInfo.getDaily().getData().get(i).getWindBearing(),
                    weatherInfo.getDaily().getData().get(i).getCloudCover(), weatherInfo.getDaily().getData().get(i).getUvIndex(),
                    weatherInfo.getDaily().getData().get(i).getUvIndexTime(), weatherInfo.getDaily().getData().get(i).getVisibility(),
                    weatherInfo.getDaily().getData().get(i).getOzone(), weatherInfo.getDaily().getData().get(i).getTemperatureMin(),
                    weatherInfo.getDaily().getData().get(i).getTemperatureMinTime(), weatherInfo.getDaily().getData().get(i).getTemperatureMax(),
                    weatherInfo.getDaily().getData().get(i).getTemperatureMaxTime(), weatherInfo.getDaily().getData().get(i).getApparentTemperatureMin(),
                    weatherInfo.getDaily().getData().get(i).getApparentTemperatureMinTime(), weatherInfo.getDaily().getData().get(i).getApparentTemperatureMax(),
                    weatherInfo.getDaily().getData().get(i).getApparentTemperatureMaxTime(), 0);
            daily.add(dailyWeather);
        }
        return daily;
    }
}
