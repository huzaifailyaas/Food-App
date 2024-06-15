package com.example.first.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.first.NotificationBottom_Fragment
import com.example.first.R
import com.example.first.databinding.ActivityFrontpageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class frontpage : AppCompatActivity() {
    private lateinit var binding: ActivityFrontpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrontpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var NavController=findNavController(R.id.fragmentContainerView)
        var bottomnav=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomnav.setupWithNavController(NavController)
        binding.notificationbutton.setOnClickListener {
            val bottomSheetDialog = NotificationBottom_Fragment()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }
    }
}