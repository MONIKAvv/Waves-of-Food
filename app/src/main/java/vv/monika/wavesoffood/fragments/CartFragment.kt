package vv.monika.wavesoffood.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.PaymentActivity
import vv.monika.wavesoffood.R
import vv.monika.wavesoffood.adapter.CartAdapter
import vv.monika.wavesoffood.databinding.FragmentCartBinding
import vv.monika.wavesoffood.model.CartItemModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var foodName: MutableList<String>
    private lateinit var foodPrice: MutableList<String>
    private lateinit var foodDescription: MutableList<String>
    private lateinit var foodImageUri: MutableList<String>
    private lateinit var foodIngredient: MutableList<String>
    private lateinit var quantity: MutableList<Int>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var userId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        retrieveCartItems()


//        dummy data
        val cartFoodName =
            listOf("Burger", "sandwich", "momo", "chat", "chawmin", "sandwich", "momo")
        val cartFoodPrice = listOf("$5", "$7", "$7", "$3", "$9", "$3", "$5")
        val cartFoodImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu5,
            R.drawable.menu4,
            R.drawable.menu6,
            R.drawable.menu7
        )
        val adapter = CartAdapter(
            ArrayList(cartFoodName),
            ArrayList(cartFoodPrice),
            ArrayList(cartFoodImage),
            requireContext()
        )
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        proceedToBuy()

        binding.root.setOnClickListener {
            val intent = Intent(requireContext(), DetailedActivity::class.java)
            startActivity(intent)

        }

        return binding.root
    }

    private fun retrieveCartItems() {
//        database reference to the firebase
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid ?: ""
        val foodRef: DatabaseReference =
            database.reference.child("user").child(userId).child("CartItems")

//        now we will create list to store cartItems
        foodName = mutableListOf()
        foodPrice = mutableListOf()
        foodDescription = mutableListOf()
        foodImageUri = mutableListOf()
        foodIngredient = mutableListOf()
        quantity = mutableListOf()

//        fetch data from database
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    //                    get cartitem object from child node
            val cartItems = foodSnapshot.getValue(CartItemModel::class.java)
//add cart items details to the list
                    cartItems?.adapterFoodName?.let { foodName.add(it) }
                    cartItems?.adapterFoodPrice?.let { foodPrice.add(it) }
                    cartItems?.adapterFoodDescription?.let { foodDescription.add(it) }
                    cartItems?.foodImage?.let { foodImageUri.add(it) }
                    cartItems?.foodQuantity?.let { quantity.add(it) }
                    cartItems?.adapterFoodIngredient?.let { foodIngredient.add(it) }

                }

                cartAdapter()

            }

            private fun cartAdapter() {
                val adapter = CartAdapter(foodName, foodPrice, foodImageUri, requireContext(),foodDescription,
                    quantity, foodIngredient)
                binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL, false)
                binding.cartRecyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "no food fetched", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun proceedToBuy() {
        binding.proceedButton.setOnClickListener {
            startActivity(Intent(requireContext(), PaymentActivity::class.java))
        }
    }

    companion object {

    }
}