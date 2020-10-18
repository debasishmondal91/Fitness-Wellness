package com.example.fitnesswellness.data.model

import com.google.gson.annotations.SerializedName

data class OffersDetailsResponse(
    @SerializedName("APICODERESULT")
    val aPICODERESULT: String,
    @SerializedName("result")
    val result: Result,
    @SerializedName("statusCode")
    val statusCode: Int
)