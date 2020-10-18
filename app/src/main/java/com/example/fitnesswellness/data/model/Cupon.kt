package com.example.fitnesswellness.data.model

import com.google.gson.annotations.SerializedName

data class Cupon(
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("title")
    val title: String
)