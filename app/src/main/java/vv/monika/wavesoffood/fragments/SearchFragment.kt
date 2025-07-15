package vv.monika.wavesoffood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import vv.monika.wavesoffood.R
import vv.monika.wavesoffood.adapter.MenuAdapter
import vv.monika.wavesoffood.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter : MenuAdapter
    private val originalMenuFoodName = listOf("Burger", "sandwitch", "momo", "kuch bhi", "kya bhai", "kuch bhi", "kya bhai")
    private val originalMenuFoodPrice =
        listOf("$5", "$4", "$6", "$7", "$8", "$7", "$8")
   private val originalMenuFoodImage = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu1, R.drawable.menu3, R.drawable.menu4,)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuFoodPrice = mutableListOf<String>()
    private val filteredMenuFoodImage = mutableListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

//        adapter = MenuAdapter(filteredMenuFoodName, filteredMenuFoodPrice, filteredMenuFoodImage,requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter



//        setup for search view
        setUpSearchView()
//        show all menu
        showAllMenu()

        return binding.root
    }

    private fun showAllMenu() {
        filteredMenuFoodName.clear()
        filteredMenuFoodPrice.clear()
        filteredMenuFoodImage.clear()

        filteredMenuFoodName.addAll(originalMenuFoodName)
        filteredMenuFoodPrice.addAll(originalMenuFoodPrice)
        filteredMenuFoodImage.addAll(originalMenuFoodImage)

        adapter.notifyDataSetChanged()
    }

    private fun setUpSearchView(){
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
        filteredMenuFoodName.clear()
        filteredMenuFoodPrice.clear()
        filteredMenuFoodImage.clear()
        originalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query, ignoreCase =  true)){
                filteredMenuFoodName.add(foodName)
                filteredMenuFoodPrice.add(originalMenuFoodPrice[index])
                filteredMenuFoodImage.add(originalMenuFoodImage[index])

            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}


//to remove the navigation bottom bar at search time use windowsoftinputmode = adjustpan at manifest file

