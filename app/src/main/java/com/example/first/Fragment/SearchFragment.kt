package com.example.first.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.first.R
import com.example.first.chatadapter.MenuAdapter
import com.example.first.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    private val originalMenuFoodName = listOf("burger", "sandwich", "pizza", "steak", "sandwich", "pizza", "steak", )
    private val originalMenuItemPrice = listOf("$5", "$7", "$15", "$40", "$7", "$15", "$40",)
    private val originalMenuImages = listOf(
        R.drawable.menu11,
        R.drawable.menu22,
        R.drawable.banner11,
        R.drawable.menu44,
        R.drawable.menu22,
        R.drawable.banner11,
        R.drawable.menu44
    )

    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuItemPrice = mutableListOf<String>()
    private val filterMenuImage = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        adapter = MenuAdapter(filterMenuFoodName, filterMenuItemPrice, filterMenuImage,requireContext())
        binding.menuRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecycleView.adapter = adapter

        // Set up the search view
        setupSearchView()

        // Show all menu items
        showAllMenu()

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAllMenu() {
        filterMenuFoodName.clear()
        filterMenuItemPrice.clear()
        filterMenuImage.clear()

        filterMenuFoodName.addAll(originalMenuFoodName)
        filterMenuItemPrice.addAll(originalMenuItemPrice)
        filterMenuImage.addAll(originalMenuImages)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
        filterMenuFoodName.clear()
        filterMenuItemPrice.clear()
        filterMenuImage.clear()

        originalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query, ignoreCase = true)) {
                filterMenuFoodName.add(foodName)
                filterMenuItemPrice.add(originalMenuItemPrice[index])
                filterMenuImage.add(originalMenuImages[index])
            }
        }
        adapter.notifyDataSetChanged()
    }
}
