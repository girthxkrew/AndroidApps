package com.ryan.redditbrowser.Retrofit.Data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("after")
    val after: String,
    @SerializedName("before")
    val before: String,
    @SerializedName("children")
    val children: MutableList<Children>,
    @SerializedName("dist")
    val dist: Int,
    @SerializedName("modhash")
    val modhash: String
)