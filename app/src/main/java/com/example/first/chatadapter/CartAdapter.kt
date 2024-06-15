package com.example.first.chatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.first.databinding.CartItemBinding

class CartAdapter(private val CartItems:MutableList<String>, private val CartitemPrice:MutableList<String>, private val cartImage:MutableList<Int>):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val itemQuantities = IntArray(CartItems.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding=CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
       holder.bind(position)
    }

    override fun getItemCount(): Int = CartItems.size


    inner class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val quantity = itemQuantities[position]
            binding.apply {
                cartFoodname.text = CartItems[position]
                CartItemPrice.text = CartitemPrice[position]
                CartImage.setImageResource(cartImage[position])
                cartItemQuantity.text = quantity.toString()

                MinusButton.setOnClickListener {
                    decreaseQuantity(position)
                }

                PlusButton.setOnClickListener {
                    increaseQuantity(position)
                }

                DeleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION ){
                        deleteItems(itemPosition)
                    }
                }
            }
        }
        private fun deleteItems(position: Int){
            CartItems.removeAt(position)
            cartImage.removeAt(position)
            CartitemPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, CartItems.size)
        }

        private fun decreaseQuantity(position: Int){
            if (itemQuantities[position]>1){
                itemQuantities[position]--
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int){
            if (itemQuantities[position]<10){
                itemQuantities[position]++
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }
    }
}
