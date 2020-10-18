package com.example.fitnesswellness.data.api

import com.example.fitnesswellness.data.model.OffersDetailsResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getOffersDetails(): OffersDetailsResponse = apiService.getOffersAndDetails()
}