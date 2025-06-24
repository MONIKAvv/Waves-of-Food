package vv.monika.wavesoffood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.databinding.PreviouslyBuyItemBinding
import java.io.ObjectInputValidation

class BuyPreviousItemAgainAdapter (private val buyPreviousFoodAgainName: ArrayList<String>, private val buyPreviousFoodAgainPrice: ArrayList<String>, private val buyPreviousFoodAgainImage:ArrayList<Int>) :
RecyclerView.Adapter<BuyPreviousItemAgainAdapter.BuyPreviousItemAgainViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuyPreviousItemAgainViewHolder {
//      view ko hold karte hai
        val binding = PreviouslyBuyItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return BuyPreviousItemAgainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyPreviousItemAgainViewHolder, position: Int) {
       holder.bind(buyPreviousFoodAgainName[position], buyPreviousFoodAgainPrice[position], buyPreviousFoodAgainImage[position])
    }

    override fun getItemCount(): Int {
      return buyPreviousFoodAgainName.size
    }

    class BuyPreviousItemAgainViewHolder (private val binding: PreviouslyBuyItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(foodName: String, foodPrice: String, foodImage: Int) {
            binding.buyAgainFoodName.text = foodName
            binding.buyAgainFoodPrice.text = foodPrice
            binding.buyAgainFoodImage.setImageResource(foodImage)
//            now we need to call this adapter to our history fragment
        }

    }
}
