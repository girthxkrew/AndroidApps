package com.ryan.darkskyweatherapp.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
CurrentWeather is the entity class that represents the table named "current"
 */
@Entity(tableName = "current")
public class CurrentWeather {
    @PrimaryKey()
    private long id;
    @ColumnInfo(name = "latitude")
    private double latitude;
    @ColumnInfo(name = "longitude")
    private double longitude;
    @ColumnInfo(name = "timezone")
    private String timezone;
    @ColumnInfo(name = "time")
    private long time;
    @ColumnInfo(name = "summary")
    private String summary;
    @ColumnInfo(name = "icon")
    private String icon;
    @ColumnInfo(name = "nearestStormDistance")
    private double nearestStormDistance;
    @ColumnInfo(name = "precipIntensity")
    private double precipIntensity;
    @ColumnInfo(name = "precipProbability")
    private double precipProbability;
    @ColumnInfo(name = "temperature")
    private double temperature;
    @ColumnInfo(name = "apparentTemperature")
    private double apparentTemperature;
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
    @ColumnInfo(name = "windBearing")
    private double windBearing;
    @ColumnInfo(name = "cloudCover")
    private double cloudCover;
    @ColumnInfo(name = "uvIndex")
    private double uvIndex;
    @ColumnInfo(name = "visibility")
    private double visibility;
    @ColumnInfo(name = "ozone")
    private double ozone;

    public long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public long getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public double getNearestStormDistance() {
        return nearestStormDistance;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
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

    public double getWindBearing() {
        return windBearing;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public double getVisibility() {
        return visibility;
    }

    public double getOzone() {
        return ozone;
    }

    public CurrentWeather(long id, double latitude, double longitude, String timezone,
                          long time, String summary, String icon, double nearestStormDistance, double precipIntensity,
                          double precipProbability, double temperature, double apparentTemperature, double dewPoint,
                          double humidity, double pressure, double windSpeed, double windGust, double windBearing,
                          double cloudCover, double uvIndex, double visibility, double ozone) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.nearestStormDistance = nearestStormDistance;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
        this.uvIndex = uvIndex;
        this.visibility = visibility;
        this.ozone = ozone;


    }
}