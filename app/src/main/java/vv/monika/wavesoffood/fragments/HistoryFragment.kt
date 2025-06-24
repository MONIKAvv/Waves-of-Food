package vv.monika.wavesoffood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import vv.monika.wavesoffood.R
import vv.monika.wavesoffood.adapter.BuyPreviousItemAgainAdapter
import vv.monika.wavesoffood.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
private lateinit var buyAgainAdapter : BuyPreviousItemAgainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView(){
        val buyAgainFoodName = arrayListOf("Momo", "crab fry", "icecream", "salad")
        val buyAgainFoodPrice = arrayListOf("$6", "$5", "$8", "$9")
        val buyAgainFoodImage = arrayListOf(R.drawable.menu1, R.drawable.menu3, R.drawable.menu7, R.drawable.menu4)

        buyAgainAdapter = BuyPreviousItemAgainAdapter(buyAgainFoodName, buyAgainFoodPrice, buyAgainFoodImage)
        binding.buyAgainRecyclerView.adapter = buyAgainAdapter
        binding.buyAgainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {

    }
}