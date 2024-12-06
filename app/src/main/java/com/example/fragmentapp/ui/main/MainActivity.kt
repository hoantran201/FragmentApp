package com.example.fragmentapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentapp.ui.create.CreateActivity
import com.example.fragmentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var viewPager: ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let {
            setContentView(it.root)

            viewPager = binding?.viewPager
            val mainPagerAdapter = MainPagerAdapter(this)
            viewPager?.adapter = mainPagerAdapter

            it.topBar.btnBack.setOnClickListener {
                val currentItem = viewPager?.currentItem
                if (currentItem == 0) {
                    finishAffinity()
                } else onBackPressed()
            }

            it.topBar.btnAdd.setOnClickListener {
                val intent = Intent(this, CreateActivity::class.java)
                startActivity(intent)
            }

            it.btNavBar.btnHome.setOnClickListener {
                selectViewPagerCurrentItem(0, false)

            }

            it.btNavBar.btnNotification.setOnClickListener {
                selectViewPagerCurrentItem(1, false)
            }

            it.btNavBar.btnInfo.setOnClickListener {
                selectViewPagerCurrentItem(2, false)
            }

            it.btNavBar.btnSetting.setOnClickListener {
                selectViewPagerCurrentItem(3, false)
            }
        }
    }

    fun selectViewPagerCurrentItem(index: Int, isVisible: Boolean) {
        viewPager?.setCurrentItem(index, isVisible)
    }

    override fun onBackPressed() {
        if (viewPager?.currentItem == 0) {
            super.onBackPressed()
            finish()
        } else {
            viewPager?.let {
                it.currentItem = it.currentItem - 1
            }
        }
    }
}
