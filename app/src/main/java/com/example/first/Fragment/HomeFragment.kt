package com.example.first.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.first.MenuBottomSheetFragment
import com.example.first.R
import com.example.first.chatadapter.PopularAdapter
import com.example.first.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding // Declare binding variable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment using data binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.viewMenu.setOnClickListener{
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner11, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner22, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner33, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object :ItemClickListener{

            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemMessage = "Selected image $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })
        val foodName= listOf("burger","sandwich","pizza","steak")
        val price= listOf("$5","$7","$15","$40")
        val PopularFoodImages =
            listOf(R.drawable.menu11,
                R.drawable.menu22,
                R.drawable.banner11,
                R.drawable.menu44)

        val adapter = PopularAdapter(foodName,price,PopularFoodImages,requireContext())
        binding.PopularRecycleView.layoutManager= LinearLayoutManager(requireContext())
        binding.PopularRecycleView.adapter=adapter

    }

}
