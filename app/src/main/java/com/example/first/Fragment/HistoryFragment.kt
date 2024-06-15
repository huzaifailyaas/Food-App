package com.example.first.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.first.R
import com.example.first.chatadapter.BuyAgainAdapter
import com.example.first.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentHistoryBinding.inflate(layoutInflater,container,false)
        setUpRecycleView()
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun setUpRecycleView(){
        val buyAgainFoodName= arrayListOf("Food1","Food2","Food3","Food4","Food5")
        val buyAgainFoodPrice= arrayListOf("$10","$10","$10","$10","$10")
        val buyAgainFoodImage=
            listOf(
                R.drawable.menu11,
                R.drawable.menu22,
                R.drawable.menu33,
                R.drawable.menu44,
                R.drawable.banner11,
                R.drawable.banner22,
            )
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.BuyAgainRecyclerView
        binding.BuyAgainRecyclerView.layoutManager=LinearLayoutManager(context)
    }

}