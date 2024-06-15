package com.example.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.first.chatadapter.MenuAdapter
import com.example.first.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentMenuBottomSheetBinding.inflate(inflater,container,false)


        binding.buttonback.setOnClickListener{
            dismiss()
        }
        val menuFoodName=listOf("burger","sandwich","pizza","steak","sandwich","pizza","steak")
        val menuIemprice= listOf("$5","$7","$15","$40","$7","$15","$40")
        val menuFoodImages =
            listOf (
                R.drawable.menu11,
                R.drawable.menu22,
                R.drawable.banner11,
                R.drawable.menu44,
                R.drawable.menu22,
                R.drawable.banner11,
                R.drawable.menu44)

        val adapter = MenuAdapter(ArrayList(menuFoodName),ArrayList(menuIemprice),ArrayList(menuFoodImages),requireContext())
        binding.menuRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecycleView.adapter=adapter
        return binding.root
    }

    }
