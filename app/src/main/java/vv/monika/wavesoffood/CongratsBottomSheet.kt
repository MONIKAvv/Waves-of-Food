package vv.monika.wavesoffood

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import vv.monika.wavesoffood.databinding.FragmentCongratsBottomSheetBinding

class CongratsBottomSheet : BottomSheetDialogFragment() {
private lateinit var binding: FragmentCongratsBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCongratsBottomSheetBinding.inflate(layoutInflater, container, false)

//        bottom sheet open with the help of show(supportfragment, string) not with intent


binding.goHomeButton.setOnClickListener {
    startActivity(Intent(requireContext(), MainActivity::class.java))
}

        return binding.root
    }

    companion object {

    }
}