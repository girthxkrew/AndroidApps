package com.ryan.darkskyweatherapp.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/*
DailyWeather is an entity class that represents the table named "daily"
 */
@Entity(tableName = "daily",
        foreignKeys = @ForeignKey(entity = CurrentWeather.class,
                                    parentColumns = "id",
                                    childColumns = "weatherId",
                                    onDelete = ForeignKey.CASCADE))
public class DailyWeather {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "weatherId")
    private long weatherId;
    @ColumnInfo(name = "default_summary")
    private String defaultSummary;
    @ColumnInfo(name = "default_icon")
    private String defaultIcon;
    @ColumnInfo(name = "time")
    private long time;
    @ColumnInfo(name = "daily_summary")
    private String dailySummary;
    @ColumnInfo(name = "daily_icon")
    private String dailyIcon;
    @ColumnInfo(name = "sunriseTime")
    private double sunriseTime;
    @ColumnInfo(name = "sunsetTime")
    private double sunsetTime;
    @ColumnInfo(name = "moonPhase")
    private double moonPhase;
    @ColumnInfo(name = "precipIntensity")
    private double precipIntensity;
    @ColumnInfo(name = "precipIntensityMax")
    private double precipIntensityMax;
    @ColumnInfo(name = "precipIntensityMaxTime")
    private double precipIntensityMaxTime;
    @ColumnInfo(name = "precipProbability")
    private double precipProbability;
    @ColumnInfo(name = "precipType")
    private String precipType;
    @ColumnInfo(name = "temperatureHigh")
    private double temperatureHigh;
    @ColumnInfo(name = "temperatureHighTime")
    private double temperatureHighTime;
    @ColumnInfo(name = "temperatureLow")
    private double temperatureLow;
    @ColumnInfo(name = "temperatureLowTime")
    private double temperatureLowTime;
    @ColumnInfo(name = "apparentTemperatureHigh")
    private double apparentTemperatureHigh;
    @ColumnInfo(name = "apparentTemperatureHighTime")
    private double apparentTemperatureHighTime;
    @ColumnInfo(name = "apparentTemperatureLow")
    private double apparentTemperatureLow;
    @ColumnInfo(name = "apparentTemperatureLowTime")
    private double apparentTemperatureLowTime;
    @ColumnInfo(name = "dewPoint")
    private double dewPoint;
    @ColumnInfo(name = "humidity")
    private double humidity;
    @ColumnInfo(name = "pressure")
    private double pressure;
    @ColumnInfo(name = "windSpeed")
    private double windSpeed;
    @ColumnInfo(name = "windGust")
    private double windGust;
    @ColumnInfo(name = "windGustTime")
    private double windGustTime;
    @ColumnInfo(name = "windBearing")
    private double windBearing;
    @ColumnInfo(name = "cloudCover")
    private double cloudCover;
    @ColumnInfo(name = "uvIndex")
    private double uvIndex;
    @ColumnInfo(name = "uvIndexTime")
    private double uvIndexTime;
    @ColumnInfo(name = "visibility")
    private double visibility;
    @ColumnInfo(name = "ozone")
    private double ozone;
    @ColumnInfo(name = "temperatureMin")
    private double temperatureMin;
    @ColumnInfo(name = "temperatureMinTime")
    private double temperatureMinTime;
    @ColumnInfo(name = "temperatureMax")
    private double temperatureMax;
    @ColumnInfo(name = "temperatureMaxTime")
    private double temperatureMaxTime;
    @ColumnInfo(name = "apparentTemperatureMin")
    private double apparentTemperatureMin;
    @ColumnInfo(name = "apparentTemperatureMinTime")
    private double apparentTemperatureMinTime;
    @ColumnInfo(name = "apparentTemperatureMax")
    private double apparentTemperatureMax;
    @ColumnInfo(name = "apparentTemperatureMaxTime")
    private double apparentTemperatureMaxTime;

    public long getId() {
        return id;
    }

    public long getWeatherId() {
        return weatherId;
    }
    public String getDefaultSummary() {
        return defaultSummary;
    }

    public String getDefaultIcon() {
        return defaultIcon;
    }

    public long getTime() {
        return time;
    }

    public String getDailySummary() {
        return dailySummary;
    }

    public String getDailyIcon() {
        return dailyIcon;
    }

    public double getSunriseTime() {
        return sunriseTime;
    }

    public double getSunsetTime() {
        return sunsetTime;
    }

    public double getMoonPhase() {
        return moonPhase;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public double getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public double getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public double getTemperatureLowTime() {
        return temperatureLowTime;
    }

    public double getApparentTemperatureHigh() {
        return apparentTemperatureHigh;
    }

    public double getApparentTemperatureHighTime() {
        return apparentTemperatureHighTime;
    }

    public double getApparentTemperatureLow() {
        return apparentTemperatureLow;
    }

    public double getApparentTemperatureLowTime() {
        return apparentTemperatureLowTime;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindGust() {
        return windGust;
    }

    public double getWindGustTime() {
        return windGustTime;
    }

    public double getWindBearing() {
        return windBearing;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public double getUvIndexTime() {
        return uvIndexTime;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getOzone() {
        return ozone;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public double getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public double getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public DailyWeather(long id, String defaultSummary, String defaultIcon, long time, String dailySummary,
                        String dailyIcon, double sunriseTime, double sunsetTime, double moonPhase,
                        double precipIntensity, double precipIntensityMax, double precipIntensityMaxTime, double precipProbability,
                        String precipType, double temperatureHigh, double temperatureHighTime, double temperatureLow,
                        double temperatureLowTime, double apparentTemperatureHigh, double apparentTemperatureHighTime,
                        double apparentTemperatureLow, double apparentTemperatureLowTime, double dewPoint, double humidity,
                        double pressure, double windSpeed, double windGust, double windGustTime, double windBearing, double cloudCover,
                        double uvIndex, double uvIndexTime, double visibility, double ozone, double temperatureMin,
                        double temperatureMinTime, double temperatureMax, double temperatureMaxTime, double apparentTemperatureMin,
                        double apparentTemperatureMinTime, double apparentTemperatureMax, double apparentTemperatureMaxTime, long weatherId) {
        this.id = id;
        this.defaultSummary = defaultSummary;
        this.defaultIcon = defaultIcon;
        this.time = time;
        this.dailySummary = dailySummary;
        this.dailyIcon = dailyIcon;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.moonPhase = moonPhase;
        this.precipIntensity = precipIntensity;
        this.precipIntensityMax = precipIntensityMax;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperatureHigh = temperatureHigh;
        this.temperatureHighTime = temperatureHighTime;
        this.temperatureLow = temperatureLow;
        this.temperatureLowTime = temperatureLowTime;
        this.apparentTemperatureHigh = apparentTemperatureHigh;
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
        this.apparentTemperatureLow = apparentTemperatureLow;
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windGustTime = windGustTime;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
        this.uvIndex = uvIndex;
        this.uvIndexTime = uvIndexTime;
        this.visibility = visibility;
        this.ozone = ozone;
        this.temperatureMin = temperatureMin;
        this.temperatureMinTime = temperatureMinTime;
        this.temperatureMax = temperatureMax;
        this.temperatureMaxTime = temperatureMaxTime;
        this.apparentTemperatureMin = apparentTemperatureMin;
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        this.apparentTemperatureMax = apparentTemperatureMax;
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
        this.weatherId = weatherId;


    }
}
