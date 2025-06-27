package vv.monika.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.databinding.PreviouslyBuyItemBinding

class BuyPreviousItemAgainAdapter (private val buyPreviousFoodAgainName: ArrayList<String>, private val buyPreviousFoodAgainPrice: ArrayList<String>, private val buyPreviousFoodAgainImage:ArrayList<Int>,
    private val requiredContext: Context) :
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
       holder.bind(buyPreviousFoodAgainName[position], buyPreviousFoodAgainPrice[position], buyPreviousFoodAgainImage[position], requiredContext)
    }

    override fun getItemCount(): Int {
      return buyPreviousFoodAgainName.size
    }

    class BuyPreviousItemAgainViewHolder (private val binding: PreviouslyBuyItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(foodName: String, foodPrice: String, foodImage: Int, requiredContext: Context) {
            binding.buyAgainFoodName.text = foodName
            binding.buyAgainFoodPrice.text = foodPrice
            binding.buyAgainFoodImage.setImageResource(foodImage)
//            now we need to call this adapter to our history fragment


            binding.root.setOnClickListener {
                val intent = Intent(requiredContext, DetailedActivity::class.java)

                intent.putExtra("FoodName", foodName)
                intent.putExtra("FoodImage", foodImage)

                requiredContext.startActivity(intent)
            }
        }

    }
}
