package com.example.testsolution.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("display_title")
    val title: String,

    @SerializedName("summary_short")
    val description: String,

    @SerializedName("src")
    val image_url: String,

    @SerializedName("multimedia")
    val multimedia: Multimedia
) : Parcelable


data class MovieResponse(
    @SerializedName("results")
    val resultList: List<Movie>,
)

@Parcelize
data class Multimedia(
    @SerializedName("src")
    val src: String
): Parcelable

