package com.example.fitnesswellness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fitnesswellness.R
import com.example.fitnesswellness.ViewModelProviderFactory
import com.example.fitnesswellness.data.api.ApiHelperImpl
import com.example.fitnesswellness.data.api.RetrofitBuilder
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        setUp()
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProvider(
                this, ViewModelProviderFactory(
                    ApiHelperImpl(RetrofitBuilder.apiService)
                )
            ).get(MainViewModel::class.java)
    }

    private fun setUp() {
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        container.adapter = fragmentAdapter
        tabs.setupWithViewPager(container)
        
    }
}