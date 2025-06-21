package vv.monika.wavesoffood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import vv.monika.wavesoffood.adapter.MenuAdapter
import vv.monika.wavesoffood.databinding.FragmentMenuBottomSheetBinding


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
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
        val menuFoodName = listOf(

            "sandwich",
            "momo",
            "chat",
            "chawmin",
            "sandwich",
            "momo",
            "sandwich",
            "momo",
            "chat",
            "chawmin",
            "sandwich",
            "momo"
        )
        val menuFoodPrice =
            listOf("$5", "$7", "$7", "$3", "$9", "$3", "$5", "$7", "$3", "$9", "$3", "$5")
        val menuFoodImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu7,
            R.drawable.menu6,
            R.drawable.menu5,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu7,
            R.drawable.menu6,
            R.drawable.menu5,
        )
        val adapter =
            MenuAdapter(ArrayList(menuFoodName), ArrayList(menuFoodPrice), ArrayList(menuFoodImage))
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        return binding.root
    }

    companion object {

    }
}


//now create a adapter