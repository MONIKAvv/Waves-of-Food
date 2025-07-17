package vv.monika.wavesoffood

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback.DismissEvent
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import vv.monika.wavesoffood.databinding.ActivityDetailedBinding
import vv.monika.wavesoffood.model.CartItemModel

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedBinding
    private var foodName: String? = null
    private var foodImage: String? = null
    private var foodDescription: String? = null
    private var foodIngredient: String? = null
    private var foodPrice: String? = null

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

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
//save food items to the particular user, we need database and user id
            val database: DatabaseReference = FirebaseDatabase.getInstance().reference
            val userId = auth.currentUser?.uid?:""

//            create a cartItem object
            val cartItem = CartItemModel(foodName.toString(), foodPrice.toString(), foodDescription.toString(),
                foodImage.toString(), foodQuantity = 1)

//            save to the user ref to the firebase
            database.child("user").child(userId).child("CartItems").push().setValue(cartItem).addOnSuccessListener {
                Toast.makeText(this, "Food Added to Cart! 😎", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Not added to cart 😣", Toast.LENGTH_SHORT).show()
            }
//            now we added to fooditem to the user id database now  we need to show that added item to our cart , we need changes into our cartadapter and cartactivity
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