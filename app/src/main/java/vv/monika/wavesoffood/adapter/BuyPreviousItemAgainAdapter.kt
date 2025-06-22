package vv.monika.wavesoffood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.databinding.PreviouslyBuyItemBinding

class BuyPreviousItemAgainAdapter (private val buyPreviousFoodAgainName: ArrayList<String>, private val buyPreviousFoodAgainPrice: ArrayList<String>, private val buyPreviousFoodAgainImage:ArrayList<Int>) :
RecyclerView.Adapter<BuyPreviousItemAgainAdapter.BuyPreviousItemAgainViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuyPreviousItemAgainViewHolder {
//      view ko hold karte hai
        val binding = PreviouslyBuyItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    }

    override fun onBindViewHolder(holder: BuyPreviousItemAgainViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
      return buyPreviousFoodAgainName.size
    }

    class BuyPreviousItemAgainViewHolder {

    }
}
