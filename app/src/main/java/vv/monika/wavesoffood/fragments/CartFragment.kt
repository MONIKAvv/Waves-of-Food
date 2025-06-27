package vv.monika.wavesoffood.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.PaymentActivity
import vv.monika.wavesoffood.R
import vv.monika.wavesoffood.adapter.CartAdapter
import vv.monika.wavesoffood.databinding.FragmentCartBinding

class CartFragment : Fragment() {
 private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentCartBinding.inflate(inflater, container, false)


//        dummy data
        val cartFoodName = listOf("Burger", "sandwich", "momo", "chat", "chawmin", "sandwich", "momo")
        val cartFoodPrice = listOf("$5", "$7", "$7", "$3", "$9", "$3", "$5")
        val cartFoodImage = listOf(
            R.drawable.menu1, R.drawable.menu2,R.drawable.menu3, R.drawable.menu5, R.drawable.menu4, R.drawable.menu6,
            R.drawable.menu7
        )
        val adapter = CartAdapter(ArrayList(cartFoodName), ArrayList(cartFoodPrice),ArrayList(cartFoodImage), requireContext())
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        proceedToBuy()

        binding.root.setOnClickListener {
            val intent = Intent(requireContext(), DetailedActivity::class.java)
            startActivity(intent)

        }

        return binding.root
    }
    private fun proceedToBuy(){
        binding.proceedButton.setOnClickListener {
            startActivity(Intent(requireContext(), PaymentActivity::class.java))
        }
    }

    companion object {

    }
}