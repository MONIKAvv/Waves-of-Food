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
import vv.monika.wavesoffood.MenuBottomSheetFragment
import vv.monika.wavesoffood.R
import vv.monika.wavesoffood.adapter.PopularAdapter
import vv.monika.wavesoffood.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
   private lateinit var binding : FragmentHomeBinding

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
        return binding.root


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



        imagesSlider.setItemClickListener(object : ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
              val itemPosition = imageList[position]
                val itemMessage =  "Selected Image $position"

                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })
//        dummy data data that needs to show to recycler view
        val foodName = listOf("Burger", "sandwitch", "momo", "kuch bhi", "kya bhai")
        val price = listOf("$5", "$4", "$6", "$7", "$8")
        val popularFoodImages = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu1)
//        set adapter
        val adapter = PopularAdapter(foodName, popularFoodImages, price, requireContext())
        binding.homePopularRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.homePopularRecyclerView.adapter = adapter

    }

    companion object {

    }
}