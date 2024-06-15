package com.example.first.Splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.first.MainActivity
import com.example.first.R
import com.example.first.intro.welcome

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, welcome::class.java)
            startActivity(intent)
            finish() },5000)
    }
}