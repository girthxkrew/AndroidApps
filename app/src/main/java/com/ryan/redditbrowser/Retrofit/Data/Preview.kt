package com.ryan.redditbrowser.Retrofit.Data


import com.google.gson.annotations.SerializedName

data class Preview(
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("images")
    val images: List<Image>
)