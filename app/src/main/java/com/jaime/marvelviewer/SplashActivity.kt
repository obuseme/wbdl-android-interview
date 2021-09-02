package com.jaime.marvelviewer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jaime.marvelviewer.databinding.ActivitySplashBinding
import com.jaime.marvelviewer.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Simple Splash Activity delaying for 3 seconds to show Lottie Animation
 */
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.animationView.setOnClickListener {
            Toast.makeText(this, resources.getString(R.string.easter_egg), Toast.LENGTH_LONG).show()
        }

        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }
}