package com.example.fragmentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.fragmentapp.adapter.ViewPagerAdapter
import com.example.fragmentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let {
            setContentView(it.root)

            val viewPager: ViewPager = it.viewPager
            val adapter = ViewPagerAdapter(supportFragmentManager)
            viewPager.adapter = adapter

            it.topToolsBar.btnBack.setOnClickListener {
                val currentItem = viewPager.currentItem
                if(currentItem == 0){
                    finishAffinity()
                } else onBackPressed()
            }

            it.topToolsBar.btnAdd.setOnClickListener {
                val intent = Intent(this, CreateActivity::class.java)
                startActivity(intent)
            }

            it.bottomNavBar.btnHome.setOnClickListener {
                viewPager.setCurrentItem(0, true)
            }

            it.bottomNavBar.btnNotification.setOnClickListener {
                viewPager.setCurrentItem(1, true)
            }

            it.bottomNavBar.btnInfo.setOnClickListener {
                viewPager.setCurrentItem(2, true)
            }

            it.bottomNavBar.btnSetting.setOnClickListener {
                viewPager.setCurrentItem(3, true)
            }
        }
    }
}
