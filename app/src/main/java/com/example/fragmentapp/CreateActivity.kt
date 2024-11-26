package com.example.fragmentapp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.ComponentActivity

class CreateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_create)
        val toolsBar = findViewById<View>(R.id.topToolsBar) // Đây là layout đã include
        val btnAdd = toolsBar.findViewById<ImageButton>(R.id.btnAdd)

        btnAdd.visibility = View.GONE

        toolsBar.findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            onBackPressed()
        }
    }
}