package vv.monika.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.databinding.PopularItemBinding

class PopularAdapter (private val items: List<String>,private val image: List<Int>, private val price: List<String>, private val requiredContext : Context):RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        return PopularViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val itemPrice = price[position]
        holder.bind(item, itemPrice, images, requiredContext)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    class PopularViewHolder (private val binding : PopularItemBinding): RecyclerView.ViewHolder(binding.root){
       private val imagesView = binding.popularFoodImage
        fun bind(item: String, price: String, images: Int, requiredContext: Context){


            binding.popularFoodName.text = item
            binding.popularFoodPrice.text = price
            imagesView.setImageResource(images)



            binding.root.setOnClickListener {
                val intent = Intent(requiredContext,DetailedActivity::class.java )
                intent.putExtra("FoodName", item)
                intent.putExtra("FoodImage", images)

                requiredContext.startActivity(intent)
            }
        }
    }
}