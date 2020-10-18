package com.example.fitnesswellness.ui

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fitnesswellness.ui.fragment.details.DetailsFragment
import com.example.fitnesswellness.ui.fragment.offers.OffersFragment

@SuppressLint("WrongConstant")
class MyPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return when (position) {

            0 -> {
                OffersFragment.newInstance()
            }
            else -> {
                return DetailsFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Offers"
            else -> {
                return "Details"
            }
        }
    }
}