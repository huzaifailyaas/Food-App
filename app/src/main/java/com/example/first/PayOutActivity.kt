package com.example.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.first.databinding.ActivityPayOutBinding

class PayOutActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPayOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_pay_out)
        binding.placemyOrder.setOnClickListener {
            val bottomsheetDialog = CongratsBottomSheet()
            bottomsheetDialog.show(supportFragmentManager,"Test")
        }
    }
}