package vv.monika.wavesoffood

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback.DismissEvent
import vv.monika.wavesoffood.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedBinding
    private var foodName: String? = null
    private var foodImage: String? = null
    private var foodDescription: String? = null
    private var foodIngredient: String? = null
    private var foodPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        store value to our defined variables
//         ensabme menuadapter se value put karni h name me
        foodName = intent.getStringExtra("MenuItemName")
        foodDescription = intent.getStringExtra("menuItemDescription")
        foodImage = intent.getStringExtra("menuItemImage")
        foodPrice = intent.getStringExtra("MenuItemPrice")
        foodIngredient = intent.getStringExtra("menuItemIngredient")

        with(binding) {
            detailFoodName.text = foodName
            detailFoodDescription.text = foodDescription
            detailFoodIngredient.text = foodIngredient
            Glide.with(this@DetailedActivity).load(Uri.parse(foodImage)).into(detailFoodImage)
        }

        binding.backButton.setOnClickListener {
            finish()

        }


        binding.addToCartButton.setOnClickListener {

        }

//        fetch data from cartadapter
//        val foodName = intent.getStringExtra("FoodName")
//        val foodPrice = intent.getStringExtra("FoodPrice")
//        val foodImage = intent.getIntExtra("FoodImage", 0)
//        now need to set to the ui
//        binding.detailedFoodName.text = foodName
//        binding.detailedFoodImage.setImageResource(foodImage)


//            val bottonSheet = CongratsBottomSheet()
//            bottonSheet.show(supportFragmentManager, "payment bottom sheet")

//        }

    }
}