package vv.monika.wavesoffood

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import vv.monika.wavesoffood.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.dontHaveAccText.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }


    }
}