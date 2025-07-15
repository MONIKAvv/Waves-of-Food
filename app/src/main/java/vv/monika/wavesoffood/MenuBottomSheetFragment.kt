package vv.monika.wavesoffood

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import vv.monika.wavesoffood.adapter.MenuAdapter
import vv.monika.wavesoffood.databinding.FragmentMenuBottomSheetBinding
import vv.monika.wavesoffood.model.menuModel


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
//    we want that when user click on view popular , the things that we have added into our backend from admin will display here

    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<menuModel>

    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)
        binding.buttonBack.setOnClickListener {
            dismiss()
        }
//        dummy data
//        val menuFoodName = listOf(
//
//            "sandwich",
//            "momo",
//            "chat",
//            "chawmin",
//            "sandwich",
//            "momo",
//            "sandwich",
//            "momo",
//            "chat",
//            "chawmin",
//            "sandwich",
//            "momo"
//        )
//        val menuFoodPrice =
//            listOf("$5", "$7", "$7", "$3", "$9", "$3", "$5", "$7", "$3", "$9", "$3", "$5")
//        val menuFoodImage = listOf(
//            R.drawable.menu1,
//            R.drawable.menu2,
//            R.drawable.menu3,
//            R.drawable.menu4,
//            R.drawable.menu7,
//            R.drawable.menu6,
//            R.drawable.menu5,
//            R.drawable.menu3,
//            R.drawable.menu4,
//            R.drawable.menu7,
//            R.drawable.menu6,
//            R.drawable.menu5,
//        )
//        val adapter =
//            MenuAdapter(ArrayList(menuFoodName), ArrayList(menuFoodPrice), ArrayList(menuFoodImage),requireContext())

        retrieveMenuItems()

        return binding.root
    }

    private fun retrieveMenuItems() {
//        retrive food items here
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children){
                    val menuItem= foodSnapshot.getValue(menuModel::class.java)
                  menuItem?.let { menuItems.add(it) }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }
    private fun setAdapter(){
        if(menuItems.isNotEmpty()){
            val adapter = MenuAdapter(menuItems , requireContext())
            binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.menuRecyclerView.adapter = adapter
        }else{
            Log.d("Items","setAdapter data not set")
        }
    }

    companion object {

    }
}


//now create a adapter