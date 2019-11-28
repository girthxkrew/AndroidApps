package com.ryan.redditbrowser.Retrofit

import com.ryan.redditbrowser.Retrofit.Data.RedditResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditAPI {

    @GET("{subreddit}/.json?limit=10")
    fun getData(@Path("subreddit") subreddit: String): Single<RedditResponse>

    companion object {
        fun create(): RedditAPI {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://www.reddit.com/r/")
                .build()

            return retrofit.create(RedditAPI::class.java)
        }
    }
}