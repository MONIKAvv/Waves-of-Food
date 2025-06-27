package vv.monika.wavesoffood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback.DismissEvent
import vv.monika.wavesoffood.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()

        }

//        fetch data from cartadapter
        val foodName = intent.getStringExtra("FoodName")
        val foodPrice = intent.getStringExtra("FoodPrice")
        val foodImage = intent.getIntExtra("FoodImage", 0)
//        now need to set to the ui
        binding.detailedFoodName.text = foodName
        binding.detailedFoodImage.setImageResource(foodImage)


        binding.addToCart.setOnClickListener {
//            val bottonSheet = CongratsBottomSheet()
//            bottonSheet.show(supportFragmentManager, "payment bottom sheet")

        }

    }
}