package com.example.fitnesswellness.data.api

import com.example.fitnesswellness.data.model.OffersDetailsResponse

interface ApiHelper {

    suspend fun getOffersDetails(): OffersDetailsResponse
}