package com.example.fitnesswellness.data.api

import com.example.fitnesswellness.data.model.OffersDetailsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("testing_data")
    suspend fun getOffersAndDetails(): OffersDetailsResponse
}