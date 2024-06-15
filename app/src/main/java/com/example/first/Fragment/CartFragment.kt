package com.example.first.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.first.CongratsBottomSheet
import com.example.first.PayOutActivity
import com.example.first.R
import com.example.first.chatadapter.CartAdapter
import com.example.first.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)

        val cartFoodName=listOf("burger","sandwich","pizza","steak")
        val cartIemprice= listOf("$5","$7","$15","$40")
        val cartFoodImages =
            listOf(R.drawable.menu11,R.drawable.menu22, R.drawable.banner11, R.drawable.menu44)

        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartIemprice),ArrayList(cartFoodImages))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter=adapter

        binding.Proceedbutton.setOnClickListener {
            val intent = Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }


}