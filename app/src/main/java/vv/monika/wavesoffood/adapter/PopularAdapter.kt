package vv.monika.wavesoffood.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.databinding.PopularItemBinding

class PopularAdapter (private val items: List<String>,private val image: List<Int>, private val price: List<String>):RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        return PopularViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val itemPrice = price[position]
        holder.bind(item, itemPrice, images)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    class PopularViewHolder (private val binding : PopularItemBinding): RecyclerView.ViewHolder(binding.root){
       private val imagesView = binding.popularFoodImage
        fun bind(item:String, price: String,images: Int){
            binding.popularFoodName.text = item
            binding.popularFoodPrice.text = price
            imagesView.setImageResource(images)

        }
    }
}