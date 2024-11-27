package com.example.fragmentapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fragmentapp.ui.HomeFragment
import com.example.fragmentapp.ui.InfoFragment
import com.example.fragmentapp.ui.NotificationFragment
import com.example.fragmentapp.ui.SettingFragment


class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> NotificationFragment()
            2 -> InfoFragment()
            else -> SettingFragment()
        }
    }
}
