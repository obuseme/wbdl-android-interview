package com.jaime.marvelviewer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jaime.marvelviewer.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Simple Splash Activity delaying for 3 seconds to show Lottie Animation
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}