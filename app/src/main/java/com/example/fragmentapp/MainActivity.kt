package com.example.fragmentapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fragmentapp.ui.HomeFragment
import com.example.fragmentapp.ui.InfoFragment
import com.example.fragmentapp.ui.NotificationFragment
import com.example.fragmentapp.ui.SettingFragment

class MainActivity : AppCompatActivity(){

    var homeFragment = HomeFragment()
    var notifiFragment = NotificationFragment()
    var infoFragment = InfoFragment()
    var settingFragment = SettingFragment()
    var frameID = R.id.fragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeTag = "homeFragmentTag"

        addFragment(homeFragment, homeTag)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(frameID)
            if(currentFragment?.tag =="homeFragmentTag"){
                finishAffinity()
            } else onBackPressed()
        }

        findViewById<ImageButton>(R.id.btnAdd).setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.btnHome).setOnClickListener {
            replaceFragment(homeFragment, homeTag)
        }

        findViewById<ImageButton>(R.id.btnNotification).setOnClickListener {
            val notifiTag = "notifiFragmentTag"
            addFragment(notifiFragment, notifiTag)
        }

        findViewById<ImageButton>(R.id.btnInfo).setOnClickListener {
            val infoTag = "infoFragmentTag"
            addFragment(infoFragment, infoTag)
        }

        findViewById<ImageButton>(R.id.btnSetting).setOnClickListener {
            val settingTag = "settingFragmentTag"
                addFragment(settingFragment, settingTag)
        }

    }

    fun addFragment(fragment: Fragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            supportFragmentManager.beginTransaction()
                .add(frameID, fragment, tag) //Tạo tag mới cho Fragment
                .addToBackStack(tag) //Name lấy Tag truyền vào luôn và đưa vào BackStack
                .commit()
        } else {
            replaceFragment(fragment, tag)
        }

    }

    fun replaceFragment(fragment: Fragment, tag: String){
        var current = supportFragmentManager.findFragmentById(R.id.fragmentContainer)?.tag
        if(current != tag ) {
            supportFragmentManager.beginTransaction()
                .replace(frameID, fragment)
                .addToBackStack(tag)
                .commit()
        }
    }
}
