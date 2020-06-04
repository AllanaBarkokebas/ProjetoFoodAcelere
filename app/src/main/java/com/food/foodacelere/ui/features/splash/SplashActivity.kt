package com.food.foodacelere.ui.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.food.foodacelere.R
import com.food.foodacelere.ui.features.login.SelectSignatureActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_TIME_HANDLER = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handle = Handler()
        handle.postDelayed({ showLogin() },
            SPLASH_TIME_HANDLER
        )
    }

    private fun showLogin() {
        val intent  = Intent(this, SelectSignatureActivity::class.java)
        startActivity(intent)
        finish()
    }
}
