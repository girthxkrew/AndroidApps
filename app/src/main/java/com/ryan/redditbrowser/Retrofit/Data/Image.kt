package com.ryan.redditbrowser.Retrofit.Data


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("resolutions")
    val resolutions: List<Resolution>,
    @SerializedName("source")
    val source: Source,
    @SerializedName("variants")
    val variants: Variants
)