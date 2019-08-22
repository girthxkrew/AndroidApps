package com.ryan.movieapplication.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ryan.movieapplication.R;
import com.ryan.movieapplication.RetroFit.MovieInfo;
import com.ryan.movieapplication.UserInterface.MovieItem;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private List<MovieInfo> movies;
    private Context context;

    public RecyclerViewAdapter(List<MovieInfo> movies, Context context)
    {
        this.movies = movies;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            constraintLayout = itemView.findViewById(R.id.constraint_layout);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_image, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w185" + movies.get(i).getPosterPath());
        if(movies.get(i).getPosterPath() == null || movies.get(i).getPosterPath().isEmpty())
        {
            Glide.with(context).load(R.drawable.nomovieposter).into(viewHolder.imageView);
        }
        else
        {
            Glide.with(context).load(uri).into(viewHolder.imageView);
        }
        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieItem.class);
                intent.putExtra("title", movies.get(i).getTitle());
                intent.putExtra("overview", movies.get(i).getOverview());
                intent.putExtra("image", movies.get(i).getPosterPath());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateMovieInfoList(List<MovieInfo> newMovieInfoList)
    {
        final MoviesDiffCallback diffCallback = new MoviesDiffCallback(movies, newMovieInfoList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        movies.clear();
        movies.addAll(newMovieInfoList);
        diffResult.dispatchUpdatesTo(this);
    }

}
