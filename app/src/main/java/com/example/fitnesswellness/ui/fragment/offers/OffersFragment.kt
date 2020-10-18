package com.example.fitnesswellness.ui.fragment.offers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.fitnesswellness.R
import com.example.fitnesswellness.ViewModelProviderFactory
import com.example.fitnesswellness.data.api.ApiHelperImpl
import com.example.fitnesswellness.data.api.RetrofitBuilder
import com.example.fitnesswellness.ui.MainViewModel
import com.example.fitnesswellness.util.Status
import kotlinx.android.synthetic.main.offers_fragment.*
import java.util.*

class OffersFragment : Fragment() {

    companion object {
        fun newInstance() =
            OffersFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offers_fragment, container, false)
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
                    it?.data?.let { result ->
                        view_pager.adapter =
                            CustomSwipeAdapter(result.result.banner, this.requireContext())
                        indicator.setupWithViewPager(view_pager, true)

                        val timer = Timer()
                        timer.scheduleAtFixedRate(
                            SliderTimer(result.result.banner, view_pager, activity),
                            4000,
                            6000
                        )

                        cupons_recycler.layoutManager =
                            LinearLayoutManager(
                                activity,
                                LinearLayout.VERTICAL,
                                false
                            ) as RecyclerView.LayoutManager?
                        cupons_recycler.adapter = CuponsRecycler(result.result.cupons, activity)
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

    /**
     * horizontal Slider for images
     */
    private class SliderTimer(
        private val images: List<String>?,
        private val viewPagerContainer: ViewPager,
        private val activity: FragmentActivity?
    ) : TimerTask() {
        override fun run() {
            activity?.runOnUiThread {
                if (viewPagerContainer.currentItem < images?.size?.minus(1)!!) {
                    viewPagerContainer.currentItem = viewPagerContainer.currentItem + 1
                } else {
                    viewPagerContainer.currentItem = 0
                }
            }
        }
    }

}