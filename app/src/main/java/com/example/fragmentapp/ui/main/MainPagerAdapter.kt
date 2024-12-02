package com.example.fragmentapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentapp.ui.fragment.HomeFragment
import com.example.fragmentapp.ui.fragment.InfoFragment
import com.example.fragmentapp.ui.fragment.NotificationFragment
import com.example.fragmentapp.ui.fragment.SettingFragment


class MainPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> NotificationFragment()
            2 -> InfoFragment()
            else -> SettingFragment()
        }
    }
}
