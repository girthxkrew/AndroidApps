package com.ryan.darkskyweatherapp.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ryan.darkskyweatherapp.R;
import com.ryan.darkskyweatherapp.RoomDatabase.Weather;
import com.ryan.darkskyweatherapp.UserInterface.RecyclerView.RecyclerViewAdapter;
import com.ryan.darkskyweatherapp.ViewModel.WeatherViewModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private WeatherViewModel weatherViewModel;
    private TextView city;
    private TextView summary;
    private TextView temperature;
    private ImageView weatherIcon;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.city);
        summary = findViewById(R.id.summary);
        temperature = findViewById(R.id.temperature);
        weatherIcon = findViewById(R.id.weatherIcon);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getApplication().getResources().getDrawable(R.drawable.line));
        recyclerView.addItemDecoration(dividerItemDecoration);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        getWeatherInfo(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
                        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                    }
                }
            }
        };

        getLocation();
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);

        } else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        getWeatherInfo(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
                    }
                    else
                    {
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    }
                }
            });
        }
    }

    private void getWeatherInfo(String latitude, String longitude) {

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.init(latitude, longitude, this);
        weatherViewModel.getDarkSkyRepository().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weatherInfo) {
                populateWeatherView(weatherInfo);
                //weatherViewModel.getWeatherFromDataBase(weatherInfo);
            }
        });

    }

    private void populateWeatherView(Weather weather)
    {
        city.setText(getAddress(weather.getCurrentWeather().getLatitude(), weather.getCurrentWeather().getLongitude()));
        summary.setText(weather.getCurrentWeather().getSummary());
        temperature.setText(Integer.toString((int)weather.getCurrentWeather().getApparentTemperature()) + "\u00B0");
        int id = R.drawable.sun;
        switch (weather.getCurrentWeather().getIcon())
        {
            case "clear-day":
                id = R.drawable.sun;
                break;
            case "clear-night":
                id = R.drawable.moon_stars;
                break;
            case "rain":
                id = R.drawable.cloud_rain;
                break;
            case "snow":
                id = R.drawable.cloud_snow;
                break;
            case "sleet":
                id = R.drawable.snow;
                break;
            case "wind":
                id = R.drawable.wind;
                break;
            case "fog":
                id = R.drawable.fog;
                break;
            case "cloudy":
                id = R.drawable.clouds;
                break;
            case "partly-cloudy-day":
                id = R.drawable.clouds_sun;
                break;
            case "partly-cloudy-night":
                id = R.drawable.clouds_moon;
                break;
        }
        Picasso.get().load(id).resize(150, 150).into(weatherIcon);
        recyclerViewAdapter = new RecyclerViewAdapter(weather.getDailyWeather(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private String getAddress(double latitude, double longitude)
    {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        String city = "";

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            city = addresses.get(0).getLocality();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1000 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        getWeatherInfo(Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
                    }
                    else
                    {
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    }
                }
            });
        }
    }
}
