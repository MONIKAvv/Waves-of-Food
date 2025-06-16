package vv.monika.wavesoffood

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.Delayed

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
//        width full and make screen behind the status bar + also add fitsystemwindow to xml for lower system navigation
        enableEdgeToEdge()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, StartAppActivity::class.java))
            finish()
        }, 3000)

    }
}