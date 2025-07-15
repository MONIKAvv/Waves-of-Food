package vv.monika.wavesoffood.adapter

import android.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.databinding.MenuItemBinding
import vv.monika.wavesoffood.model.menuModel
import kotlin.collections.get

class MenuAdapter(
//     here we want to have model data class , so we create meniitem data class in model\

    private val menuItems: List<menuModel>,

//    private val menuItemName: MutableList<String>, private val menuItemPrice: MutableList<String>, private val menuItemImage: MutableList<Int>,
    private val requiredContext: Context

) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
//            set data name price image to recycler view
        fun bind(position: Int) {
//           items show id set
            val menuItem = menuItems[position]
            binding.apply {
                menuFoodName.text = menuItem.foodName

                menuFoodPrice.text = menuItem.foodPrice
//                menuFoodImage.setImageResource(menuItemImage[position])
//we want to use glide to upload image
                val uri = Uri.parse(menuItem.foodImage)
                Glide.with(requiredContext).load(uri).into(menuFoodImage)

            }
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailActivity(position)
                }
//                val intent = Intent(requiredContext, DetailedActivity::class.java)
//
//                intent.putExtra("FoodName", menuItemName[position])
//                intent.putExtra("FoodImage", menuItemImage[position])
//                requiredContext.startActivity(intent) these are  defined below
            }

        }

        private fun openDetailActivity(position: Int) {

            val menuItem = menuItems[position]
//            intent to open detailed screen and pass data
            val intent = Intent(requiredContext, DetailedActivity::class.java).apply {
                putExtra("MenuItemName", menuItem.foodName)
                putExtra("MenuItemPrice", menuItem.foodPrice)
                putExtra("menuItemImage", menuItem.foodImage)
                putExtra("menuItemDescription", menuItem.foodDescription)
                putExtra("menuItemIngredient", menuItem.foodIngredient)
            }
            requiredContext.startActivity(intent)


        }


    }

}






