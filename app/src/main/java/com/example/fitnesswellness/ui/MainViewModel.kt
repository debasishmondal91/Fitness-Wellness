package com.example.fitnesswellness.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesswellness.data.api.ApiHelper
import com.example.fitnesswellness.data.model.OffersDetailsResponse
import com.example.fitnesswellness.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val details = MutableLiveData<Resource<OffersDetailsResponse>>()

    init {
        fetchOffersAndDetails()
    }

    private fun fetchOffersAndDetails() {
        viewModelScope.launch {
            details.postValue(Resource.loading(null))
            try {
                val detailsFromApi = apiHelper.getOffersDetails()
                if (detailsFromApi.statusCode == 200)
                    details.postValue(Resource.success(detailsFromApi))
                else
                    details.postValue(Resource.error("Error", detailsFromApi))
            } catch (e: Exception) {
                details.postValue(
                    Resource.error(
                        "Something Went Wrong, Our Best Minds Are Working On That",
                        null
                    )
                )
            }
        }
    }

    fun observeOfferDetails(): MutableLiveData<Resource<OffersDetailsResponse>> {
        return details
    }

}