package com.example.first.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.first.R
import com.example.first.main.frontpage
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var signOutBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        auth = FirebaseAuth.getInstance()
        signOutBtn = findViewById(R.id.signOutBtn)

        signOutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, frontpage::class.java))
        }
    }
}