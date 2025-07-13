package vv.monika.wavesoffood

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import vv.monika.wavesoffood.databinding.ActivitySignupBinding
import vv.monika.wavesoffood.model.userModel

class SignupActivity : AppCompatActivity() {

    private lateinit var userName: String
    private lateinit var userEmail: String
    private lateinit var userPassword: String

    //    firebase database
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient


    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
//        initialisze database
        auth = Firebase.auth
        database = Firebase.database.reference
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)

        binding.createAccountButton.setOnClickListener {
//            get user entered data
            userName = binding.name.text.toString()
            userEmail = binding.email.text.toString().trim()
            userPassword = binding.password.text.toString().trim()

            if (!(userName.isBlank() || userEmail.isBlank() || userPassword.isBlank())) {
                createUserAccount(userEmail, userPassword)

            } else {
                Toast.makeText(this, "Please Fill All the Details", Toast.LENGTH_SHORT).show()
            }

        }

        binding.alreadyHaveAccText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.googleButton.setOnClickListener {
            val signIntent = googleSignInClient.signInIntent
            launcher.launch(signIntent)
        }

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if (task.isSuccessful){
                val account: GoogleSignInAccount? = task.result
                val credential: AuthCredential = GoogleAuthProvider.getCredential(account?.idToken, null)
                auth.signInWithCredential(credential).addOnCompleteListener { task->
                    Log.d("log task", "${task.exception}")
                    if(task.isSuccessful){
                        Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Sign in Failed ", Toast.LENGTH_SHORT).show()
                        Log.d("GoogleSignin", "Failed ${task.exception}")
                    }
                }
            }else{
                Toast.makeText(this, "Sign in Failed ", Toast.LENGTH_SHORT).show()


            }
        }else{
            Toast.makeText(this, "Sign in Failed ", Toast.LENGTH_SHORT).show()
            Log.d("signIn Failure ", "failed Result code not matched $result")
        }

    }

    private fun createUserAccount(userEmail: String, userPassword: String) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show()
                saveUserData()
                startActivity(Intent(this@SignupActivity, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Account creation Failed!", Toast.LENGTH_SHORT).show()
                Log.d("Account Failed", "failed ${task.exception}")
            }
        }

    }

    private fun SignupActivity.saveUserData() {
        userName = binding.name.text.toString()
        userEmail = binding.email.text.toString().trim()
        userPassword = binding.password.text.toString().trim()
        val user = userModel(userName, userEmail, userPassword)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
//        save to database
        database.child("user").child(userId).setValue(user)

    }
}





