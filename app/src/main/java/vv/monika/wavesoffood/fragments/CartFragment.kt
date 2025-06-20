package vv.monika.wavesoffood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import vv.monika.wavesoffood.R
import vv.monika.wavesoffood.adapter.CartAdapter
import vv.monika.wavesoffood.databinding.FragmentCartBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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
        val adapter = CartAdapter(ArrayList(cartFoodName), ArrayList(cartFoodPrice),ArrayList(cartFoodImage))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        return binding.root
    }

    companion object {

    }
}