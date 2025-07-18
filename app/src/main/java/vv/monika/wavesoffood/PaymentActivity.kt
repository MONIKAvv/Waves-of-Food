package vv.monika.wavesoffood

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import vv.monika.wavesoffood.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPaymentBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var useId: String
    private lateinit var name: String
    private lateinit var address: String
    private lateinit var phoneNumber: String
    private lateinit var amount: String
    private lateinit var foodItemName: ArrayList<String>
    private lateinit var foodItemPrice: ArrayList<String>
    private lateinit var foodItemDescription: ArrayList<String>
    private lateinit var foodItemImages: ArrayList<String>
    private lateinit var foodItemIngredient: ArrayList<String>
    private lateinit var foodItemQuantities: ArrayList<Int>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        initialize firebase and user details
        auth = FirebaseAuth.getInstance()
//        set User Data
        setUserData()


        binding.placeOrderButton.setOnClickListener {
         val bottomSheet = CongratsBottomSheet()
            bottomSheet.show(supportFragmentManager, "congrats bottom sheet")
        }

    }

    private fun setUserData() {
        val user = auth.currentUser
        if(user != null){
            val userId = user.uid
            val userReference = databaseReference.child("user").child(userId)

            userReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
               if (snapshot.exists()){
                   val name = snapshot.child("name").getValue(String::class.java)
                   val address = snapshot.child("address").getValue(String::class.java)
                   val phone = snapshot.child("phone").getValue(String::class.java)
                   binding.apply {
                       userName.setText(name)
                       userAddress.setText(address)
                      userPhoneNumber.setText(phone)
                   }
               }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}