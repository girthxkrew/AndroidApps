package com.ryan.movieapplication.RecyclerView;

import android.support.v7.util.DiffUtil;

import com.ryan.movieapplication.RetroFit.MovieInfo;

import java.util.List;

public class MoviesDiffCallback extends DiffUtil.Callback {
    List<MovieInfo> oldList;
    List<MovieInfo> newList;

    public MoviesDiffCallback(List<MovieInfo> oldList, List<MovieInfo> newList)
    {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPositiion) {
        return oldList.get(oldPosition).getTitle().equals(newList.get(newPositiion).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        final MovieInfo oldMovieInfo = oldList.get(oldPosition);
        final MovieInfo newMovieInfo = newList.get(newPosition);
        return oldMovieInfo.equals(newMovieInfo);
    }
}
