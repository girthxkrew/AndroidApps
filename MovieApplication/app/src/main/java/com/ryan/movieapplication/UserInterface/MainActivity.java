package com.ryan.movieapplication.UserInterface;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.ryan.movieapplication.R;
import com.ryan.movieapplication.RecyclerView.RecyclerViewAdapter;
import com.ryan.movieapplication.RetroFit.APIHandler;
import com.ryan.movieapplication.RetroFit.Movie;
import com.ryan.movieapplication.RetroFit.MovieInfo;
import com.ryan.movieapplication.RetroFit.RetroFitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private APIHandler handler;
    private SearchView searchView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<MovieInfo> popularMoviesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        searchView = findViewById(R.id.searchView);
        searchView.setFocusable(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                searchView.clearFocus();
                if (Double.parseDouble(query) > 9 || Double.parseDouble(query) < 0) {
                    Toast.makeText(getApplicationContext(),
                            "Invalid Vote Average", Toast.LENGTH_LONG)
                            .show();

                } else {
                    Call<Movie> averageVote = handler.getVoteAverage(Double.parseDouble(query));
                    averageVote.enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                            recyclerViewAdapter.updateMovieInfoList(stripQuery(response.body().getResults(),
                                    Double.parseDouble(query)));
                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {

                        }
                    });
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        handler = RetroFitInstance.getRetrofitInstance().create(APIHandler.class);
        Call<Movie> popularMovies = handler.getPopularMovies();

        popularMovies.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                recyclerViewAdapter = new RecyclerViewAdapter(response.body().getResults(), getApplicationContext());
                recyclerView.setAdapter(recyclerViewAdapter);
                popularMoviesList = response.body().getResults();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("SHITWASAFAILURE", t.toString());

            }
        });
    }

    public List<MovieInfo> stripQuery(List<MovieInfo> list, double voteAverage)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getVoteAverage() == voteAverage)
            {
                list.remove(i);
            }
        }
        return list;
    }
}
