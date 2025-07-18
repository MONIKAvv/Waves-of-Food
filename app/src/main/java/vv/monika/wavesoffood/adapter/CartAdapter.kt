package vv.monika.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.rememberTimePickerState
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.databinding.CartItemBinding

class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemPrice: MutableList<String>,
    private val cartImage: MutableList<String>,
    private val requiredContext: Context,
    private val cartDescription: MutableList<String>,
    private val cartQuantity: MutableList<Int>,
    private val cartIngredient: MutableList<String>,
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    //    we have to intialise firebase why? because we are saving the cartItem for the particular user only
    private val auth = FirebaseAuth.getInstance()

    init {
//        initialization of firebase
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        val cartItemNumber = cartItems.size

        Companion.itemQuantities = IntArray(cartItemNumber) { 1 }
        cartItemReference = database.reference.child("user").child(userId).child("CartItems")

    }

    companion object {
        private var itemQuantities: IntArray = intArrayOf()
        private lateinit var cartItemReference: DatabaseReference
    }

//    private var itemQuantities = IntArray(cartItems.size) { 1 }

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

    //    function from cartFragment , get updated quantities
    fun getUpdatedItemsQuantities(): MutableList<Int>
    {
    val itemQuantity = mutableListOf<Int>()
        itemQuantity.addAll(cartQuantity)
        return itemQuantity

    }

    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartFoodName.text = cartItems[position]
                cartFoodPrice.text = cartItemPrice[position]

//                load image using glide
                val uriString = cartImage[position]
                val uri = Uri.parse(uriString)
                Glide.with(requiredContext).load(uri).into(cartFoodImage)

                totalCartFoodQty.text = quantity.toString()

                decreaseFoodQty.setOnClickListener {
                    decreaseQuantities(position)

                }
                increaseFoodQty.setOnClickListener {
                    increaseQuantities(position)

                }
                deleteCartItems.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        deleteItem(itemPosition)
                    }

                }
            }

            binding.root.setOnClickListener {
                val intent = Intent(requiredContext, DetailedActivity::class.java)

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
//            cartItems.removeAt(position)
//            cartItemPrice.removeAt(position)
//            cartImage.removeAt(position)
//            notifyItemRemoved(position)
//            notifyItemRangeChanged(position, cartItems.size)

//            we will get position of the item and remove it from the particular user id only
            val positionRetrieve = position
            getUniqueKeyIdPosition(positionRetrieve) { uniqueKey ->

                if (uniqueKey != null) {
                    removeItem(position, uniqueKey)
                }
            }
        }

        private fun removeItem(position: Int, uniqueKey: String) {
            if (uniqueKey != null) {
                cartItemReference.child(uniqueKey).removeValue().addOnSuccessListener {
                    cartItems.removeAt(position)
                    cartImage.removeAt(position)
                    cartDescription.removeAt(position)
                    cartItemPrice.removeAt(position)
                    cartQuantity.removeAt(position)
                    cartIngredient.removeAt(position)
                    Toast.makeText(requiredContext, "deleted successfully", Toast.LENGTH_SHORT)
                        .show()
//                    update item quantity
                    itemQuantities =
                        itemQuantities.filterIndexed { index, i -> index != position }.toIntArray()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, cartItems.size)
                }.addOnFailureListener {
                    Toast.makeText(requiredContext, "Failed to Delete", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun getUniqueKeyIdPosition(
            positionRetrieve: kotlin.Int,
            onComplete: (String?) -> Unit
        ) {
            cartItemReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey: String? = null
//                    loop for snapSort children
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        {
                            if (index == positionRetrieve) {
                                uniqueKey = dataSnapshot.key
                                return@forEachIndexed
                            }
                        }
                    }
                    onComplete(uniqueKey)


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }


}





