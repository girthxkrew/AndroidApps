package com.ryan.queryapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface API {

    @GET("volley_array.json")
    fun getMovies() : Call<List<Movie>>

    companion object
    {
        var BASE_URL = "http://35.200.174.74/apis/"

        fun create() : API
        {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(API::class.java)
        }
    }

}