package vv.monika.wavesoffood.fragments

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.os.TestLooperManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import vv.monika.wavesoffood.MenuBottomSheetFragment
import vv.monika.wavesoffood.R
import vv.monika.wavesoffood.adapter.MenuAdapter
import vv.monika.wavesoffood.adapter.PopularAdapter
import vv.monika.wavesoffood.databinding.FragmentHomeBinding
import vv.monika.wavesoffood.model.menuModel

class HomeFragment : Fragment() {
//    we need three variables here , database, binding, and menuitems from model
    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<menuModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)



        binding.viewAllMenuBtn.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager, "Test")
        }

        retrieveAndDisplayPopularItems()

        return binding.root


    }

    private fun retrieveAndDisplayPopularItems() {
//        get reference to the database
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()
//        signle event listener
        foodRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
//                Loop karna hai
                for (foodSnapshot in snapshot.children){
                    val menuItem = foodSnapshot.getValue(menuModel::class.java)
                    menuItem?.let { menuItems.add(it) }
                }

//                display random popular items
                randomPopularItems()

            }

            private fun randomPopularItems() {
//                shuffle list create, auto shuffle
                val index = menuItems.indices.toList().shuffled()
                val numItemToShow = 6
                val subSetMenuItem = index.take(numItemToShow).map { menuItems[it] }
                setPopularItemAdapter(subSetMenuItem)
            }

            private fun setPopularItemAdapter(subSetMenuItem: List<menuModel>) {
                val adapter = MenuAdapter(subSetMenuItem, requireContext())
                binding.homePopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.homePopularRecyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        set image slider
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        val imagesSlider = binding.imageSlider
        imagesSlider.setImageList(imageList)
        imagesSlider.setImageList(imageList, ScaleTypes.FIT)

//        select on click listener


        imagesSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage = "Selected Image $position"

                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })
//        dummy data data that needs to show to recycler view
//        val foodName = listOf("Burger", "sandwitch", "momo", "kuch bhi", "kya bhai")
//        val price = listOf("$5", "$4", "$6", "$7", "$8")
//        val popularFoodImages = listOf(
//            R.drawable.menu1,
//            R.drawable.menu2,
//            R.drawable.menu3,
//            R.drawable.menu4,
//            R.drawable.menu1
//        )
////        set adapter
//        val adapter = PopularAdapter(foodName, popularFoodImages, price, requireContext())
//        binding.homePopularRecyclerView.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.homePopularRecyclerView.adapter = adapter

    }

    companion object {

    }
}