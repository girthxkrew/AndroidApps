package com.ryan.darkskyweatherapp.UserInterface.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryan.darkskyweatherapp.R;
import com.ryan.darkskyweatherapp.RoomDatabase.DailyWeather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
RecyclerViewAdapter class is used to update and create views in the recycler view with
information from DailyWeather
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<DailyWeather> dailyWeatherList;
    private Context context;

    public RecyclerViewAdapter(List<DailyWeather> dailyWeatherList, Context context) {
        this.dailyWeatherList = dailyWeatherList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date dateFormat = new java.util.Date(dailyWeatherList.get(position).getTime() * 1000);
        String weekday = sdf.format(dateFormat);
        holder.day.setText(weekday);
        holder.high.setText(Integer.toString((int)dailyWeatherList.get(position).getApparentTemperatureHigh()) + "\u00B0");
        holder.low.setText(Integer.toString((int)dailyWeatherList.get(position).getApparentTemperatureLow()) + "\u00B0");
        int id = R.drawable.sun;
        String summary = "";
        switch (dailyWeatherList.get(position).getDailyIcon())
        {
            case "clear-day":
                id = R.drawable.sun;
                summary = "Clear Day";
                break;
            case "clear-night":
                id = R.drawable.moon_stars;
                summary = "Clear Night";
                break;
            case "rain":
                id = R.drawable.cloud_rain;
                summary = "Rainy";
                break;
            case "snow":
                id = R.drawable.cloud_snow;
                summary = "Snowy";
                break;
            case "sleet":
                summary = "Sleet";
                id = R.drawable.snow;
                break;
            case "wind":
                id = R.drawable.wind;
                summary = "Windy";
                break;
            case "fog":
                id = R.drawable.fog;
                summary = "Fog";
                break;
            case "cloudy":
                id = R.drawable.clouds;
                summary = "Cloudy";
                break;
            case "partly-cloudy-day":
                id = R.drawable.clouds_sun;
                summary = "Partly Cloudy Day";
                break;
            case "partly-cloudy-night":
                id = R.drawable.clouds_moon;
                summary = "Partly Cloudy Night";
                break;
        }
        holder.summary.setText(summary);
        Picasso.get().load(id).resize(100, 100).into(holder.weatherIcon);

    }

    @Override
    public int getItemCount() {
        return dailyWeatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView day;
        public TextView summary;
        public TextView low;
        public TextView high;
        public ImageView weatherIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            summary = itemView.findViewById(R.id.dailysummary);
            low = itemView.findViewById(R.id.lowTemperature);
            high = itemView.findViewById(R.id.highTemperature);
            weatherIcon = itemView.findViewById(R.id.icon);
        }
    }
}
