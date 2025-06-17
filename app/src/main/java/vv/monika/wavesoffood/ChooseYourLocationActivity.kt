package vv.monika.wavesoffood

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vv.monika.wavesoffood.databinding.ActivityChooseYourLocationBinding

class ChooseYourLocationActivity : AppCompatActivity() {
    private val binding : ActivityChooseYourLocationBinding by lazy {
        ActivityChooseYourLocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val location_list = arrayOf("Jaupur","chakai", "Bareli", "Ahamdabaad", "kolkata", "Australia")
        val adapter  = ArrayAdapter(this, android.R.layout.simple_list_item_1, location_list)
        val autoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)

    }
}