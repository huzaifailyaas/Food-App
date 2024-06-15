package com.example.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.first.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodname= intent.getStringExtra("MenuItemName")
        val foodImage= intent.getIntExtra("MenuItemImage",0)

        binding.detailFoodName.text = foodname
        binding.DetaiIFoodImage.setImageResource(foodImage)
        binding.imageButton.setOnClickListener {
            finish()
        }
    }
}