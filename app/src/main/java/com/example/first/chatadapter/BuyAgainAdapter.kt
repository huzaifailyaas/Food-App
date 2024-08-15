package com.example.first.chatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.first.databinding.BuyAgainItemBinding

class BuyAgainAdapter(
    private val buyAgainFoodName: ArrayList<String>,
    private val buyAgainFoodPrice: ArrayList<String>,
    private val buyAgainFoodImage: List<Int>,
) :
    RecyclerView.Adapter<BuyAgainAdapter.BuyAgainVIewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainVIewHolder {
        val binding =
            BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainVIewHolder(binding)

    }


    override fun onBindViewHolder(holder: BuyAgainVIewHolder, position: Int) {
        holder.bind(
            buyAgainFoodName[position],
            buyAgainFoodPrice[position],
            buyAgainFoodImage[position]
        )
    }

    override fun getItemCount(): Int = buyAgainFoodName.size

    class BuyAgainVIewHolder(private val binding: BuyAgainItemBinding) : RecyclerView.ViewHolder
        (binding.root) {
        fun bind(FoodName: String, FoodPrice: String, FoodImage: Int) {
            binding.buyAgainFoodName.text = FoodName
            binding.buyAgainFoodPrice.text = FoodPrice
            binding.BuyAgainFoodImage.setImageResource(FoodImage)
        }

    }
}