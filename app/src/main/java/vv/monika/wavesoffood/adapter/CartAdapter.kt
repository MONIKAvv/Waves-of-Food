package vv.monika.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.databinding.CartItemBinding

class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemPrice: MutableList<String>,
    private val cartImage: MutableList<Int>,
    private val requiredContext: Context
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val itemQuantities = IntArray(cartItems.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
//       data bind
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]

                cartFoodName.text = cartItems[position]
                cartFoodPrice.text = cartItemPrice[position]
                cartFoodImage.setImageResource(cartImage[position])
                totalCartFoodQty.text = quantity.toString()

                decreaseFoodQty.setOnClickListener {
                    decreaseQuantities(position)

                }
                increaseFoodQty.setOnClickListener {
                    increaseQuantities(position)

                }
                deleteCartItems.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION){
                        deleteItem(itemPosition)
                    }

                }
            }

            binding.root.setOnClickListener {
                val intent  = Intent(requiredContext, DetailedActivity::class.java)

                intent.putExtra("FoodName", cartItems[position])
                intent.putExtra("FoodImage", cartImage[position])
                intent.putExtra("FoodPrice", cartItemPrice[position])

                requiredContext.startActivity(intent)
//                now need to fetch this on detailedActivity
//                also need to update adapter in cardFragment
            }

        }

        private fun decreaseQuantities(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.totalCartFoodQty.text = itemQuantities[position].toString()
            }
        }


        private fun increaseQuantities(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.totalCartFoodQty.text = itemQuantities[position].toString()
            }

        }

        private fun deleteItem(position: Int) {
            cartItems.removeAt(position)
            cartItemPrice.removeAt(position)
            cartImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)

        }
    }
}





