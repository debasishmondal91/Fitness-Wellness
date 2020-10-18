package com.example.fitnesswellness.data.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("banner")
    val banner: List<String>,
    @SerializedName("cupons")
    val cupons: List<Cupon>,
    @SerializedName("decription_image")
    val decriptionImage: String,
    @SerializedName("description_body")
    val descriptionBody: String,
    @SerializedName("description_title")
    val descriptionTitle: String,
    @SerializedName("latitudes")
    val latitudes: String,
    @SerializedName("longitude")
    val longitude: String
)