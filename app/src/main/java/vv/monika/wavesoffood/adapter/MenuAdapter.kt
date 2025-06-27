package vv.monika.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.DetailedActivity
import vv.monika.wavesoffood.databinding.MenuItemBinding

class MenuAdapter(private val menuItemName: MutableList<String>, private val menuItemPrice: MutableList<String>, private val menuItemImage: MutableList<Int>,
    private val requiredContext: Context) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
       val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
    holder.bind(position)
    }

    override fun getItemCount(): Int = menuItemName.size

    inner class MenuViewHolder (private val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
//           items show id set
            binding.apply {
                menuFoodName.text = menuItemName[position]

                menuFoodPrice.text = menuItemPrice[position]
                menuFoodImage.setImageResource(menuItemImage[position])


            }
            binding.root.setOnClickListener {
                val intent = Intent(requiredContext, DetailedActivity::class.java)

                intent.putExtra("FoodName", menuItemName[position])
                intent.putExtra("FoodImage", menuItemImage[position])
                requiredContext.startActivity(intent)
            }

        }


    }
}




