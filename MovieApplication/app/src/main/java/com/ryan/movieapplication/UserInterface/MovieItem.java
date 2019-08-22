package com.ryan.movieapplication.UserInterface;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ryan.movieapplication.R;

public class MovieItem extends AppCompatActivity {

    private String title;
    private String overview;
    private String posterPath;
    private TextView movieOverview;
    private ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_item);
        getIntentInfo();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
            actionBar.setTitle(title);
        }
        movieOverview = findViewById(R.id.overview);
        movieOverview.setText(overview);
        movieImage = findViewById(R.id.movie_item_image_view);
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w342" + posterPath);
        if(posterPath == null || posterPath.isEmpty())
        {
            Glide.with(this).load(R.drawable.nomovieposter).into(movieImage);
        }
        else
        {
            Glide.with(this).load(uri).into(movieImage);
        }
    }

    private void getIntentInfo()
    {
        if(getIntent().hasExtra("title") && getIntent().hasExtra("overview")
            && getIntent().hasExtra("image"))
        {
            title = getIntent().getStringExtra("title");
            overview = getIntent().getStringExtra("overview");
            posterPath = getIntent().getStringExtra("image");
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
