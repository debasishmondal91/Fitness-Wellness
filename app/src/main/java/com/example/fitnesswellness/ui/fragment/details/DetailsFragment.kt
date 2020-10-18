package com.example.fitnesswellness.ui.fragment.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.fitnesswellness.R
import com.example.fitnesswellness.ViewModelProviderFactory
import com.example.fitnesswellness.data.api.ApiHelperImpl
import com.example.fitnesswellness.data.api.RetrofitBuilder
import com.example.fitnesswellness.ui.MainViewModel
import com.example.fitnesswellness.util.Status
import com.example.fitnesswellness.util.uploadImage
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.offers_fragment.*
import kotlinx.android.synthetic.main.offers_fragment.progressBar

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(
                this, ViewModelProviderFactory(
                    ApiHelperImpl(RetrofitBuilder.apiService)
                )
            ).get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.observeOfferDetails().observe(this, Observer {

            when (it.status) {

                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { result ->
                        imageView.uploadImage(result.result.decriptionImage)
                        title.text = result.result.descriptionTitle
                        description.text = result.result.descriptionBody
                    }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                }

            }
        })
    }

}